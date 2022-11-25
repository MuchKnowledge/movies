package com.example.app.presentation.navigation

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.core.net.toUri
import com.example.app.presentation.bottom_sheets.search_params.SearchParamsBottomSheet
import javax.inject.Inject

class MainRouter @Inject constructor(
    private val context: Context
) {

    fun navigateToSearchParamsBottomSheet(onClick: (String, Int) -> Unit) = SearchParamsBottomSheet(onClick)

    fun navigateToIMDbWebSite(id: String) {
        val uri = "https://www.imdb.com/title/$id/"
        val intent = Intent(Intent.ACTION_VIEW, uri.toUri())
        val chooser = Intent.createChooser(intent, "Open with").addFlags(FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(chooser)
    }
}