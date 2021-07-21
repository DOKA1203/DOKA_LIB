package com.github.doka.dokalib.discord

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Invite
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

class DiscordFunctions {
    companion object{
        val jsonParser = JSONParser()
        fun InvitefromJson(jsonObject: String,jda: JDA) : Invite?{
            val json = jsonParser.parse(jsonObject) as JSONObject
            return Invite.resolve(jda,json["code"].toString()).complete()
        }

        fun InvitefromCode(code: String,jda: JDA) : Invite?{
            return Invite.resolve(jda,code).complete()
        }
    }
}

fun Invite.toJson() : String{
    val json = JSONObject()
    json["code"] = this.code
    json["inviter"] = this.inviter!!.id
    json["uses"] = this.uses.toString()
    json["url"] = this.url
    json["guild"] = this.guild!!.id
    json["isExpanded"] = this.isExpanded.toString()
    json["channel"] = this.channel!!.id
    json["isTemporary"] = this.isTemporary.toString()
    json["maxUses"] = this.maxUses.toString()
    json["type"] = this.type.toString()
    return json.toJSONString()!!
}