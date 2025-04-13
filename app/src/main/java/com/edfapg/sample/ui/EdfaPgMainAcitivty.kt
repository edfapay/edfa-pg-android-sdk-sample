/*
 * Property of EdfaPg (https://edfapay.com).
 */

package com.edfapg.sample.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.edfapg.sample.databinding.ActivityMainBinding
import com.edfapg.sdk.model.request.card.EdfaPgCard
import com.edfapg.sdk.model.request.order.EdfaPgSaleOrder
import com.edfapg.sdk.model.request.payer.EdfaPgPayer
import com.edfapg.sdk.toolbox.EdfaPayDesignType
import com.edfapg.sdk.toolbox.EdfaPayLanguage
import com.edfapg.sdk.views.edfacardpay.EdfaCardPay
import com.edfapg.sdk.views.edfacardpay.EdfaPayWithCardDetails
import java.util.UUID

class EdfaPgMainAcitivty : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureView()
    }

    private fun configureView() {
        binding.btnSale.setOnClickListener {
            startActivity(Intent(this, EdfaPgSaleActivity::class.java))
        }
        binding.btnRecurringSale.setOnClickListener {
            startActivity(Intent(this, EdfaPgRecurringSaleActivity::class.java))
        }
        binding.btnCapture.setOnClickListener {
            startActivity(Intent(this, EdfaPgCaptureActivity::class.java))
        }
        binding.btnCreditVoid.setOnClickListener {
            startActivity(Intent(this, EdfaPgCreditvoidActivity::class.java))
        }
        binding.btnGetTransStatus.setOnClickListener {
            startActivity(Intent(this, EdfaPgGetTransStatusActivity::class.java))
        }
        binding.btnGetTransDetails.setOnClickListener {
            startActivity(Intent(this, EdfaPgGetTransDetailsActivity::class.java))
        }

        binding.btnSaleWithCardUi1.setOnClickListener {
            payWithCard(EdfaPayDesignType.one)
//            payWithCardDetails()
        }

        binding.btnSaleWithCardUi2.setOnClickListener {
            payWithCard(EdfaPayDesignType.two)
        }
        binding.btnSaleWithCardUi3.setOnClickListener {
            payWithCard(EdfaPayDesignType.three)
        }
    }

    fun payWithCardDetails() {

        val order = EdfaPgSaleOrder(
            id = UUID.randomUUID().toString(),
            amount = 0.12,
            currency = "SAR",
            description = "Test Order"
        )

        val payer = EdfaPgPayer(
            "Zohaib", "Kambrani",
            "Riyadh", "SA", "Riyadh", "123123",
            "kashifuop99@gmail.com", "966500409598",
            "171.100.100.123"
        )

//        val card = EdfaPgCard("4458 2713 2974 8293", 7, 2029, "331")
//        val card = EdfaPgCard("5452057473989962", 3, 2026, "386")
        val card = EdfaPgCard(number = "5294151606897978", expireMonth = 3, expireYear = 2026, cvv = "049")
        EdfaPayWithCardDetails(this)
            .setOrder(order)
            .setPayer(payer)
            .setCard(card)
            .onTransactionFailure { res, data ->
                print("$res $data")
                Toast.makeText(this, "Transaction Failure", Toast.LENGTH_LONG).show()
            }.onTransactionSuccess { res, data ->
                print("$res $data")
                Toast.makeText(this, "Transaction Success", Toast.LENGTH_LONG).show()
            }
            .initialize(
                onError = {
                    Toast.makeText(this, "onError $it", Toast.LENGTH_LONG).show()
                },
                onPresent = {

                }
            )


    }

    fun payWithCard(designType: EdfaPayDesignType) {

        val order = EdfaPgSaleOrder(
            id = UUID.randomUUID().toString(),
            amount = 1.00,
            currency = "SAR",
            description = "Test Order"
        )

        val payer = EdfaPgPayer(
            "Zohaib", "Kambrani",
            "Riyadh", "SA", "Riyadh", "123123",
            "a2zzuhaib@gmail.com", "966500409598",
            "171.100.100.123"
        )


        val edfaCardPay = EdfaCardPay()
            .setOrder(order)
            .setPayer(payer)
            .setDesignType(designType)
            .setLanguage(EdfaPayLanguage.en)
            .onTransactionFailure { res, data ->
                print("$res $data")
                Toast.makeText(this, "Transaction Failure", Toast.LENGTH_LONG).show()
            }.onTransactionSuccess { res, data ->
                print("$res $data")
                Toast.makeText(this, "Transaction Success", Toast.LENGTH_LONG).show()
            }

        /*
        * Precise way to start card payment (ready to use)
        * */
        edfaCardPay.initialize(
            this,
            onError = {

            },
            onPresent = {

            }
        )


        /*
        * To get intent of card screen activity to present in your own choice (ready to use)
        * */
//        startActivity(edfaCardPay.intent(
//            this,
//            onError = {
//
//            },
//            onPresent = {
//
//            })
//        )


        /*
        * To get fragment of card screen to present in your own choice (ready to use)
        * */
//        edfaCardPay.fragment(
//            onError = {
//
//            },
//            onPresent = {
//
//            }
//        )
    }
}
