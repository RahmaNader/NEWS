import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.adapter.INewsAdapter
import com.example.news.adapter.NewsAdapter
import com.example.news.model.Articles
import com.example.news.newsnetwork.ISuccessListener
import com.example.news.newsnetwork.NewsNetwork

class MainActivity : AppCompatActivity(), INewsAdapter, ISuccessListener {
    var categories = arrayOf<String?>("General", "Entertainment", "Business","Health","Science","Technology")
    lateinit var adapter: NewsAdapter
    lateinit var newsNetwork: NewsNetwork
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        newsNetwork = NewsNetwork(this)
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE

        newsNetwork.getNews("general")

        //////////drop down menu
    }


    override fun onItemClicked(article: Articles) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(article.url))

    }

    @SuppressLint("CutPasteId")
    override fun onNewsSuccess(news: ArrayList<Articles>) {
        Log.i("news count in success", "${news.size}")
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        adapter = NewsAdapter(news, this)
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(context)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
    }

}
