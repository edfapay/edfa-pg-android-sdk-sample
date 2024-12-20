/*
 * Property of EdfaPg (https://edfapay.com).
 */

package com.edfapg.sample.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edfapg.sample.databinding.ActivityMainBinding
import com.edfapg.sdk.core.EdfaPgSdk
import com.edfapg.sdk.model.request.card.EdfaPgCard
import com.edfapg.sdk.model.request.order.*
import com.edfapg.sdk.model.request.payer.*
import com.edfapg.sdk.model.response.base.error.EdfaPgError
import com.edfapg.sdk.model.response.sale.EdfaPgSaleCallback
import com.edfapg.sdk.model.response.sale.EdfaPgSaleResponse
import com.edfapg.sdk.model.response.sale.EdfaPgSaleResult
import com.edfapg.sdk.toolbox.EdfaPgUtil
import com.edfapg.sdk.views.edfacardpay.*
import com.edfapg.sdk.views.edfacardpay.creditcardview.models.CreditCard
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

        binding.btnSaleWithCardUi.setOnClickListener {
            payWithCard()
        }
    }

    fun payWithCard(){

        val order = EdfaPgSaleOrder(
            id = UUID.randomUUID().toString(),
            amount = 1.00,
            currency = "SAR",
            description = "Test Order"
        )

        val payer = EdfaPgPayer(
            "Zohaib","Kambrani",
            "Riyadh","SA", "Riyadh","123123",
            "a2zzuhaib@gmail.com","966500409598",
            "171.100.100.123"
        )

        val edfaCardPay = EdfaCardPay()
            .setOrder(order)
            .setPayer(payer)
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

    fun payWithCardDetails(){

        val order = EdfaPgSaleOrder(
            id = UUID.randomUUID().toString(),
            amount = 1.00,
            currency = "SAR",
            description = "Test Order"
        )

        val payer = EdfaPgPayer(
            "Zohaib","Kambrani",
            "Riyadh","SA", "Riyadh","123123",
            "a2zzuhaib@gmail.com","966500409598",
            "171.100.100.123"
        )

        val card = EdfaPgCard(number = "111111111111", cvv = "111", expireMonth = 12, expireYear = 2024)

        EdfaPgSdk.Adapter.SALE.execute(
            order = order,
            card = card,
            payer = payer,
            termUrl3ds = EdfaPgUtil.ProcessCompleteCallbackUrl,
            options = null,
            auth = false,
            callback = object : EdfaPgSaleCallback{

                override fun onFailure(throwable: Throwable) {
                    super.onFailure(throwable)
                }

                override fun onResponse(response: EdfaPgSaleResponse) {
                    super.onResponse(response)
                }

                override fun onResult(result: EdfaPgSaleResult) {

                }

                override fun onError(error: EdfaPgError) {

                }

            }
        )


//        val edfaCardPay = EdfaCardPay()
//            .setOrder(order)
//            .setPayer(payer)
//            .onTransactionFailure { res, data ->
//                print("$res $data")
//                Toast.makeText(this, "Transaction Failure", Toast.LENGTH_LONG).show()
//            }.onTransactionSuccess { res, data ->
//                print("$res $data")
//                Toast.makeText(this, "Transaction Success", Toast.LENGTH_LONG).show()
//            }
//
//        /*
//        * Precise way to start card payment (ready to use)
//        * */
//        edfaCardPay.initialize(
//            this,
//            onError = {
//
//            },
//            onPresent = {
//
//            }
//        )


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
