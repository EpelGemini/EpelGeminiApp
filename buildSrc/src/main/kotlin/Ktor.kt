object Ktor {
    private const val ktorVersion = "2.1.3"
    const val ktorClientCore = "io.ktor:ktor-client-core:$ktorVersion"
    const val ktorClientAndroid = "io.ktor:ktor-client-android:$ktorVersion"
    const val ktorSerialization = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
    const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
    const val ktorClientLogging = "io.ktor:ktor-client-logging:$ktorVersion"
    const val ktorClientAuth = "io.ktor:ktor-client-auth:$ktorVersion"

    private const val logBackClassicVersion = "1.2.3"
    const val logBackClassic = "ch.qos.logback:logback-classic:$logBackClassicVersion"
}