package com.aubay.pokemon.core.extensions

import android.content.Intent
import android.net.Uri

fun sendEmail(email: String, subject: String, description: String) : Intent {
    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null))
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
    emailIntent.putExtra(Intent.EXTRA_TEXT, description)
    return emailIntent
}