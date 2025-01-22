
# EdfaPay Payment Gateway Android SDK

EdfaPay is a white-label payment software provider. Thanks to our 15+ years of experience in the
payment industry, we’ve developed a state-of-the-art white-label payment system that ensures smooth
and uninterrupted payment flow for merchants across industries.

EdfaPay Android SDK was developed and designed with one purpose: to help the Android developers
easily integrate the EdfaPay API Payment Platform for a specific merchant.


## Installation
> [!IMPORTANT]
> ### Configure Repository
> 
> **Setup Android** 
> ![Release](https://jitpack.io/v/edfapay/edfa-pg-android-sdk.svg)
> ```groovy
>  implementation 'com.github.edfapay:edfa-pg-android-sdk:$VERSION' //check jetpack.io for latest version

> You must add the `jitpack` repository support to the **Gradle** to access and download the native dependency. 
>
> Add below to the `./android/build.gradle` of your project
> 
> ```groovy
> allprojects {
>     repositories {
>         ...
> 
>         // Add below at the same location 
>         maven {
>             url 'https://jitpack.io'
>         }
>     }
> }
> ```
> ----
>
> Or add below to the `./settings.gradle` of your project
> 
> ```groovy
> dependencyResolutionManagement {
>     repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
>     repositories {
>         ...
> 
>         // Add below at the same location 
>         maven {
>             url "https://jitpack.io"
>         }
>     }
> }
> ```
>
> 
> [!IMPORTANT]
> ### Configuring the Proguard Rule
>
> **Android**
> 
> If your project is obfuscated with proguard, please add the rule below to your android project **proguard-rules.pro**
> 
> ```
> -keep class com.edfapg.sdk.** {
>   public protected private *;
> }
> ```

## Usage
> [!IMPORTANT]
> ### Initialize SDK
> 
> ```kotlin
> val edfaCardPay = EdfaCardPay()
> edfaCardPay.initialize(  
>    this,  
>    onError = {  
>  
>   },  
>    onPresent = {  
>
>  })
> ```

> [!TIP]
> ### Get Ready for Payment
> > **Create `EdfaPgSaleOrder` Model**
> > ```kotlin
> >     val order = EdfaPgSaleOrder(
> >     	id = UUID.randomUUID().toString(),  
> >			amount = 1.00,  
> >			currency = "SAR",  
> >			description = "Test Order"
> >    	);
> > ```
>
> > **Create `EdfaPgPayer` Model**
> > ```kotlin
> >    val payer = EdfaPgPayer(
> >        firstName = "First Name",
> >        lastName = "Last Name",
> >        address = "EdfaPay Payment Gateway",
> >        country = "SA",
> >        city = "Riyadh",
> >        zip = "123768",
> >        email = "support@edapay.com",
> >        phone = "+966500409598",
> >        ip = "66.249.64.248",
> >        options = EdfaPgPayerOption( // Options
> >        middleName = "Middle Name",
> >        birthdate = DateTime.parse("1987-03-30"),
> >        address2 = "Usman Bin Affan",
> >        state = "Al Izdihar"
> >        )
> >    );
> > ```
> 
> > **Payment with Card**
> > ```dart
> > import com.edfapg.sdk.toolbox.EdfaPayDesignType  
> >import com.edfapg.sdk.toolbox.EdfaPayLanguage
> >
> >    EdfaCardPay()
> >        .setOrder(order)
> >        .setPayer(payer)
> >        .setDesignType(EdfaPayDesignType.one)  
> >        .setLanguage(EdfaPayLanguage.en)
> >        .onTransactionSuccess((response){
> >          print("onTransactionSuccess.response ===> ${response.toString()}");
> >
> >    }).onTransactionFailure((response){
> >      print("onTransactionFailure.response ===> ${response.toString()}");
> >
> >    }).onError((error){
> >      print("onError.response ===> ${error.toString()}");
> >
> >    }).initialize(context);
> > ```
>
> > **Pay With ApplePay - iOS Only**
> > ```dart
> >     EdfaApplePay()
> >         .setOrder(order)
> >         .setPayer(payer)
> >         .setApplePayMerchantID(APPLEPAY_MERCHANT_ID)
> >         .onAuthentication((response){
> >       print("onAuthentication.response ===> ${response.toString()}");
> > 
> >     }).onTransactionSuccess((response){
> >       print("onTransactionSuccess.response ===> ${response.toString()}");
> > 
> >     }).onTransactionFailure((response){
> >       print("onTransactionFailure.response ===> ${response.toString()}");
> > 
> >     }).onError((error){
> >       print("onError.response ===> ${error.toString()}");
> > 
> >     }).initialize(context);
> > ```
>
> ### Addon's
> > **Create [EdfaPgSaleOrder](https://github.com/edfapay/edfa-pg-android-sdk/blob/master/edfa-pg-sdk/src/main/java/com/edfapg/sdk/model/request/order/EdfaPgSaleOrder.kt) ` & ` [EdfaPgPayer](https://github.com/edfapay/edfa-pg-android-sdk/blob/master/edfa-pg-sdk/src/main/java/com/edfapg/sdk/model/request/payer/EdfaPgPayer.kt)
> > 
> > **Create `EdfaPgSaleOption` Model**
> > ```dart
> >     final saleOption = EdfaPgSaleOption(
> >         channelId = "channel-id-here", // channel-id if its enable for merchant
> >         recurringInit = true // Make sure recurring is enabled for merchant and [true=if want to do recurring, false=if don't want do recurring]
> >     )
> > ```
> > 
> > **Create `EdfaPgCard` Model**
> > ```dart
> >    val card = EdfaPgCard(
> >		number = "5294151606897978", 
> > 	expireMonth = 3, 
> > 	expireYear = 2026, 
> > 	cvv = "049"
> >		)
> > ```
> > 
> > **Sale Transaction** - Make sure to pass null to `saleOption:` and false to `isAuth:`
> > ```dart
> >   EdfaPgSdk.Adapter.SALE.execute(  
 > >   order = order,  
 > >   card = card,  
 > >   payer = payer,  
 > >   termUrl3ds = termUrl3ds,  
 > >   options = saleOptions,  
 > >   auth = isAuth,  
 > >   callback = object : EdfaPgSaleCallback {  
 > >       override fun onResponse(response: EdfaPgSaleResponse) {  
 > >           super.onResponse(response)  
 > >           println(response.preattyPrint())  
 > > 
 > >       }  
 > >       override fun onResult(result: EdfaPgSaleResult) {  
 > >           println(result.preattyPrint())  
 > > 
 > >       }  
 > >       override fun onError(error: EdfaPgError) = Unit  
 > >       override fun onFailure(throwable: Throwable) {  
 > >           super.onFailure(throwable)  
 > >           println(throwable.preattyPrint())  
 > >       }  
 > >   }  
> >)    
> > ```
>
> > **Recurring Transaction**
> > - Make sure to pass false to `isAuth:`
> > - Card Number should be passed the same used for the first `Sale` with `EdfaPgSaleOption.recurringInit==true`
> > - `EdfaPgRecurringOptions.firstTransactionId:` should `transactionId` from first success `Sale` with `EdfaPgSaleOption.recurringInit==true`
> > - `EdfaPgRecurringOptions.token:` Should be recurringToken from first success `Sale` with `EdfaPgSaleOption.recurringInit==true`
> > ```dart
> >    EdfaPgSdk.Adapter.RECURRING_SALE.execute(  
> >    order = order,  
> >    options = recurringOptions,  
> >    payerEmail = selectedTransaction.payerEmail,  
> >    cardNumber = selectedTransaction.cardNumber,  
> >    auth = isAuth,  
> >    callback = object : EdfaPgSaleCallback {  
> >        override fun onResponse(response: EdfaPgSaleResponse) {  
> >            super.onResponse(response)  
> >           println(response.preattyPrint())  
> >        }  
> >  
> >        override fun onResult(result: EdfaPgSaleResult) {  
> >            println(result.preattyPrint())  
> >        }  
> >  
> >        override fun onError(error: EdfaPgError) = Unit  
> >  
> >        override fun onFailure(throwable: Throwable) {  
> >            super.onFailure(throwable)  
> >            println(throwable.preattyPrint())  
> >        }  
> >    }  
> >)
> > ```
> 
> > **Capture Transaction**
> > - `transactionId:` should `transactionId` from success `Sale` with `isAuth:true`
> > - Card Number should be passed the same used for the `Sale` with `isAuth:true`
> > - `cardNumber:` should authorized by `Sale` with `isAuth:true`
> > - `amount:` should be the same as `Sale` with `isAuth:true`
> > ```dart
> >    EdfaPgSdk.Adapter.CAPTURE.execute(  
> >    transactionId = selectedTransaction.id,  
> >    payerEmail = selectedTransaction.payerEmail,  
> >    cardNumber = selectedTransaction.cardNumber,  
> >    amount = amount,  
> >    callback = object : EdfaPgCaptureCallback {  
> >        override fun onResponse(response: EdfaPgCaptureResponse) {  
> >            super.onResponse(response)  
> >           println(response.preattyPrint())  
> >        }  
> >  
> >        override fun onResult(result: EdfaPgCaptureResult) {  
> >            println(result.preattyPrint())  
> >        }  
> >  
> >        override fun onError(error: EdfaPgError) = Unit  
> >  
> >        override fun onFailure(throwable: Throwable) {  
> >            super.onFailure(throwable)  
> >            println(throwable.preattyPrint())  
> >  
> >        }  
> >    }  
> >)
> > ```
>
> > **Credit Void Transaction**
> > - `transactionId:` should `transactionId` from success `Sale` with `isAuth:true`
> > - Card Number should be passed the same used for the `Sale` with `isAuth:true`
> > - `cardNumber:` should authorized by `Sale` with `isAuth:true`
> > - `amount:` should be the same as `Sale` with `isAuth:true`
> > ```dart
> >    EdfaPgSdk.Adapter.CREDITVOID.execute(  
> >    transactionId = selectedTransaction.id,  
> >    payerEmail = selectedTransaction.payerEmail,  
> >    cardNumber = selectedTransaction.cardNumber,  
> >    amount = amount,  
> >    callback = object : EdfaPgCreditvoidCallback {  
> >        override fun onResponse(response: EdfaPgCreditvoidResponse) {  
> >            super.onResponse(response)  
> >           println(response.preattyPrint())  
> >        }  
> >  
> >        override fun onResult(result: EdfaPgCreditvoidResult) {  
> >            println(result.preattyPrint())  
> >        }  
> >  
> >        override fun onError(error: EdfaPgError) = Unit  
> >  
> >        override fun onFailure(throwable: Throwable) {  
> >            super.onFailure(throwable)  
> >            println(throwable.preattyPrint())  
> >        }  
> >    }  
> >)
> > ```
>
> > **Transaction Detail**
> > - `transactionId:` should be from the last transaction,
> > - `cardNumber:` should be passed the same used for the last transaction
> > ```dart
> >     EdfaPgSdk.Adapter.GET_TRANSACTION_DETAILS.execute(  
> >    transactionId = selectedTransaction.id,  
> >    payerEmail = selectedTransaction.payerEmail,  
> >    cardNumber = selectedTransaction.cardNumber,  
> >    callback = object : EdfaPgGetTransactionDetailsCallback {  
> >        override fun onResponse(response: EdfaPgGetTransactionDetailsResponse) {  
> >            super.onResponse(response)  
> >          println(response.preattyPrint())  
> >        }  
> >  
> >        override fun onResult(result: EdfaPgGetTransactionDetailsResult)  {  
> >            println(result.preattyPrint())  
> >        }  
> >  
> >        override fun onError(error: EdfaPgError) {  
> >            println(error.preattyPrint())  
> >        }  
> >  
> >        override fun onFailure(throwable: Throwable) {  
> >            super.onFailure(throwable)  
> >            println(throwable.preattyPrint())  
> >        }  
> >    }  
> >)
> > ```
>
> > **Transaction Status**
> > - `transactionId:` should be from the last transaction,
> > - `cardNumber:` should be passed the same used for the last transaction
> > ```dart
> >     EdfaPgSdk.Adapter.GET_TRANSACTION_STATUS.execute(  
> >    transactionId = selectedTransaction.id,  
> >    payerEmail = selectedTransaction.payerEmail,  
> >    cardNumber = selectedTransaction.cardNumber,  
> >    callback = object : EdfaPgGetTransactionStatusCallback {  
> >        override fun onResponse(response: EdfaPgGetTransactionStatusResponse) {  
> >            super.onResponse(response)  
> >             
> >            println(response.preattyPrint())  
> >        }  
> >  
> >        override fun onResult(result: EdfaPgGetTransactionStatusResult) {  
> >            println(result.preattyPrint())  
> >        }  
> >  
> >        override fun onError(error: EdfaPgError) {  
> >            println(error.preattyPrint())  
> >        }  
> >  
> >        override fun onFailure(throwable: Throwable) {  
> >            super.onFailure(throwable)  
> >            println(throwable.preattyPrint())  
> >  
> >        }  
> >    }  
> >)
> > ```


## Getting help

To report a specific issue or feature request, open
a [new issue](https://github.com/edfapay/edfa-pg-android-sdk-sample/issues).

Or write a direct letter to the [support@edfapay.sa](mailto:support@edfapay.sa).

## License

MIT License. See
the [LICENSE](https://github.com/edfapay/edfa-pg-android-sdk-sample/blob/main/LICENSE) file for
more details.

## Contacts

![](/media/footer.png)

Website: https://edfapay.com/home/
Phone: [+966920031242](tel:+966920033633)  
Email: [support@edfapay.sa](mailto:support@edfapay.sa)  
7637 Othman Bin Affan St., 2123 Al Ezdihar Dist., 12487 Riyadh, Riyadh, Saudi Arabia

© 2022 - 2023 EdfaPay. All rights reserved.
