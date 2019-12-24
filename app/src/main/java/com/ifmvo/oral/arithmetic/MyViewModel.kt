package com.ifmvo.oral.arithmetic

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import java.util.*

/* 
 * (●ﾟωﾟ●)
 * 
 * Created by Matthew Chen on 2019-12-24.
 */
class MyViewModel(app: Application) : AndroidViewModel(app) {

    private val handle by lazy { SavedStateHandle() }

    var winFlag = false

    companion object {
        const val SP_NAME = "ifmvo"

        const val KEY_HIGH_SCORE = "key_high_score"
        const val KEY_LEFT_NUMBER = "key_left_number"
        const val KEY_RIGHT_NUMBER = "key_right_number"
        const val KEY_ANSWER = "key_answer"
        const val KEY_CURRENT_SCORE = "key_current_score"
        const val KEY_OPERATER = "key_operater"
    }

    init {
        val sp = getApplication<Application>().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        handle.set(KEY_HIGH_SCORE, sp.getInt(KEY_HIGH_SCORE, 0))
        handle.set(KEY_LEFT_NUMBER, sp.getInt(KEY_LEFT_NUMBER, 0))
        handle.set(KEY_RIGHT_NUMBER, sp.getInt(KEY_RIGHT_NUMBER, 0))
        handle.set(KEY_ANSWER, sp.getInt(KEY_ANSWER, 0))
        handle.set(KEY_CURRENT_SCORE, sp.getInt(KEY_CURRENT_SCORE, 0))
        handle.set(KEY_OPERATER, sp.getString(KEY_OPERATER, "+"))
    }

    fun getHighScore(): MutableLiveData<Int> {
        return handle.getLiveData(KEY_HIGH_SCORE)
    }

    fun getLeftNumber(): MutableLiveData<Int> {
        return handle.getLiveData(KEY_LEFT_NUMBER)
    }

    fun getRightNumber(): MutableLiveData<Int> {
        return handle.getLiveData(KEY_RIGHT_NUMBER)
    }

    fun getAnswer(): MutableLiveData<Int> {
        return handle.getLiveData(KEY_ANSWER)
    }

    fun getCurrentScore(): MutableLiveData<Int> {
        return handle.getLiveData(KEY_CURRENT_SCORE)
    }

    fun getOperator(): MutableLiveData<String> {
        return handle.getLiveData(KEY_OPERATER)
    }

    fun generate() {
        val level = 20
        val random = Random()
        val x = random.nextInt(level) + 1
        val y = random.nextInt(level) + 1

        if (x % 2 == 0) {
            getOperator().value = "+"
            if (x > y) {
                getLeftNumber().value = x - y
                getRightNumber().value = y
                getAnswer().value = x
            } else {
                getLeftNumber().value = y - x
                getRightNumber().value = x
                getAnswer().value = y
            }
        } else {
            getOperator().value = "-"
            if (x > y) {
                getLeftNumber().value = x
                getRightNumber().value = y
                getAnswer().value = x - y
            } else {
                getLeftNumber().value = y
                getRightNumber().value = x
                getAnswer().value = y - x
            }
        }

    }

    fun save() {
        val sp = getApplication<Application>().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putInt(KEY_HIGH_SCORE, getHighScore().value ?: 0)
        edit.apply()
    }

    fun answerCorrect() {
        getCurrentScore().value = (getCurrentScore().value ?: 0) + 1
        if ((getCurrentScore().value ?: 0) > (getHighScore().value ?: 0)) {
            getHighScore().value = getCurrentScore().value ?: 0
            winFlag = true
        }
        generate()
    }
}