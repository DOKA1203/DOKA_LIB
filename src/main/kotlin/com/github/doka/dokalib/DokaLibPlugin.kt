package com.github.doka.dokalib

import com.github.doka.dokalib.events.DateChangeEvent
import com.okkero.skedule.SynchronizationContext
import com.okkero.skedule.schedule
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.time.LocalDate


class DokaLibPlugin : JavaPlugin() , Listener{
    companion object{
        lateinit var Instance:DokaLibPlugin
    }

    override fun onEnable() {
        Instance = this
        Bukkit.getPluginManager().registerEvents(this, Instance)

        //LocalDateTime
        Bukkit.getScheduler().schedule(Instance, SynchronizationContext.ASYNC) {
            repeating(20)
            var date: LocalDate = LocalDate.now()
            while (true){
                if(date.dayOfMonth != LocalDate.now().dayOfMonth){
                    val event: DateChangeEvent = DateChangeEvent()
                    switchContext(SynchronizationContext.SYNC)
                    Bukkit.getPluginManager().callEvent(event)
                    switchContext(SynchronizationContext.ASYNC)
                }
                date = LocalDate.now()
                yield()
            }
        }
    }

    @EventHandler
    private fun dateChange(event: DateChangeEvent){
        Bukkit.broadcastMessage("DATE CHANGED")
    }
}
