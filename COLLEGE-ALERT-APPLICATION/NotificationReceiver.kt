class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val eventTitle = intent.getStringExtra("eventTitle")
        val builder = NotificationCompat.Builder(context, "EVENTS_CHANNEL")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Event Reminder")
            .setContentText("Don't forget: $eventTitle")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(0, builder.build())
    }
}
