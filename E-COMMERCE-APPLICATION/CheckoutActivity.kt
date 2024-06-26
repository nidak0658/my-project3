class CheckoutActivity : AppCompatActivity() {

    private lateinit var stripe: Stripe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Initialize Stripe
        stripe = Stripe(this, "your_publishable_key")

        // Create a payment method and confirm the payment
        val cardInputWidget = findViewById<CardInputWidget>(R.id.cardInputWidget)
        val paymentMethodCreateParams = cardInputWidget.paymentMethodCreateParams
        if (paymentMethodCreateParams != null) {
            val confirmParams = ConfirmPaymentIntentParams
                .createWithPaymentMethodCreateParams(paymentMethodCreateParams, "your_client_secret")
            stripe.confirmPayment(this, confirmParams)
        }
    }
}
