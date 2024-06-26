class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private val eventList = mutableListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        eventAdapter = EventAdapter(eventList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = eventAdapter

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            // Launch AddEventActivity to add a new event
        }

        loadEvents()
    }

    private fun loadEvents() {
        // Load events from database or add some sample data
        eventList.add(Event(id = "1", title = "Orientation", date = "2024-09-01"))
        eventList.add(Event(id = "2", title = "Sports Day", date = "2024-09-10"))
        eventAdapter.notifyDataSetChanged()
    }
}

private fun scheduleNotification(context: Context, event: Event) {
    val notificationIntent = Intent(context, NotificationReceiver::class.java)
    notificationIntent.putExtra("eventTitle", event.title)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val triggerTime = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 9) // Example: set notification for 9 AM
    }.timeInMillis

    alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
}
