package config

import java.util.Properties

object Config {

    var cacheSize = 5
        private set

    init {
        val properties = Properties()
        properties.load(ClassLoader.getSystemResourceAsStream("cache.size"))

        cacheSize = properties.getProperty("cache.size", cacheSize.toString()).toInt()
    }
}