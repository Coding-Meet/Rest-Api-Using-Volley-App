package com.coding.meet.restapiusingvolley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.coding.meet.restapiusingvolley.adapters.ProductAdapter
import com.coding.meet.restapiusingvolley.models.Product
import com.coding.meet.restapiusingvolley.models.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productRV = findViewById<RecyclerView>(R.id.productRV)
        val loadingPB = findViewById<ProgressBar>(R.id.loadingPB)

        callProductApi(productRV, loadingPB)
    }

    private fun callProductApi(productRV: RecyclerView, loadingPB: ProgressBar) {
        loadingPB.visibility = View.VISIBLE
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://fakestoreapi.com/products"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
//                val productList = callManuallyData(response)

                val productList = callAutomaticData(response)


                val productAdapter = ProductAdapter()
                productRV.adapter = productAdapter
                loadingPB.visibility = View.GONE
                productAdapter.submitList(productList)

            },
            { error ->
                error.printStackTrace()
                loadingPB.visibility = View.GONE
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })

        queue.add(stringRequest)
    }

    // Manually Json Convert Data Class
    private fun callManuallyData(response: String): ArrayList<Product> {
        val responseArray = JSONArray(response)
        val productList = arrayListOf<Product>()

        for (index in 0 until responseArray.length()) {
            val productObj = responseArray.getJSONObject(index)

            val ratingObj = productObj.getJSONObject("rating")

            val product = Product(
                productObj.getInt("id"),
                productObj.getString("title"),
                productObj.getString("image"),
                Rating(
                    ratingObj.getInt("count"),
                    ratingObj.getDouble("rate")
                ),
                productObj.getDouble("price")
            )
            productList.add(product)
        }

        return productList
    }

    /// Automatic json to data class convert
    private fun callAutomaticData(response: String): ArrayList<Product> {
        return Gson().fromJson(
            response,object :TypeToken<ArrayList<Product>>() {}.type
        )
    }
}