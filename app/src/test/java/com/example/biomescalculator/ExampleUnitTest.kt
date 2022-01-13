package com.example.biomescalculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ProbabilityStats {
    @Test
    fun small_collection1() {
        val WpnList = mutableListOf(0,1)
        val WpnPow = mutableListOf(0,1)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = listOf(0.0,1.0)
        assertEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection2() {
        val WpnList = mutableListOf(1,0)
        val WpnPow = mutableListOf(0,1)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = listOf(1.0)
        assertEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection3() {
        val WpnList = mutableListOf(1,0)
        val WpnPow = mutableListOf(1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = listOf(0.0,1.0)
        assertEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection4a() {
        val WpnList = mutableListOf(1,1)
        val WpnPow = mutableListOf(1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = listOf(.5,.5)
        assertEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection4b() {
        val WpnList = mutableListOf(2,1)
        val WpnPow = mutableListOf(1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = listOf(1.0/3,2.0/3)
        assertEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection5() {
        val WpnList = mutableListOf(1,1,1)
        val WpnPow = mutableListOf(2,1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = listOf(1.0/3,1.0/3,1.0/3)
        assertEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection6() {
        val WpnList = mutableListOf(1,1,1)
        val WpnPow = mutableListOf(1,1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = listOf(1.0/3,2.0/3)
        assertEquals(TargetProb, ProbList)
    }
}