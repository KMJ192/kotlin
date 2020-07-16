package com.example.grammar.Async

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_async.*

class AsyncActivity : AppCompatActivity() {

    var task : BackgroundAsyncTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        //Async Window Start & Stop
        start.setOnClickListener {
            task = BackgroundAsyncTask(progressbar, ment)
            task?.execute()
        }
        stop.setOnClickListener {
            task?.cancel(true)
        }
    }

    //앱이 내려가면 잠시 멈추는 기능
    override fun onPause() {
        task?.cancel(true)
        super.onPause()
    }
}

//Parameter => ProgressBar, TextView
class BackgroundAsyncTask(val progressbar : ProgressBar, val progressText : TextView) : AsyncTask<Int, Int, Int>(){
    //실행 순서 onPreExecute -> doInBackground -> onProgressUpdate -> onPostExecute
    var percent : Int = 0

    override fun onPreExecute() {
        percent = 0
        progressbar.setProgress(percent) //시작단계의 퍼센트
    }
    //AsyncTask Parameter
    //params -> doInBackground에서 사용할 타입
    //progress -> onProgressUpdate에서 사용할 타입
    //result -> onPostExecute에서 사용할 타입
    override fun doInBackground(vararg params: Int?): Int {
        //완료가 되지 않았을 경우
        while(isCancelled() == false){
            percent++
            Log.d("async", "value : $percent%" )
            if(percent > 100){
                break
            }else{
                publishProgress(percent)
            }
            try{
                //progressbar을 보기위하여 0.05초 씩 delay
                Thread.sleep(50)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
        return percent
    }
    //publishProgress(percent) -> onProgressUpdate(vararg values : Int?)
    //percent -> values
    //doInBackground에서 percent가 1씩 올라갈 때 마다 onProgressUpdate를 통하여 update
    override fun onProgressUpdate(vararg values: Int?) {
        progressbar.setProgress(values[0] ?: 0) //values[0]이 null일 때 0을 입력
        progressText.setText("퍼센트 : " + values[0])
        super.onProgressUpdate(*values)
    }

    //완료 되었을 경우
    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        progressText.setText("작업이 완료되었습니다.")
    }

    //취소가 되었을 경우 호출되는 onCancelled
    override fun onCancelled() {
        progressbar.setProgress(0)
        progressText.setText("작업이 취소되었습니다.")
    }
}