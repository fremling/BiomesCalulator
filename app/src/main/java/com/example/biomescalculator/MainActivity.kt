package com.example.biomescalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biomescalculator.databinding.ActivityMainBinding

//Varialbe for binding
lateinit var binding: ActivityMainBinding

val NoWeaponsList = mutableListOf(0, 0, 0)
val WeaponNameList = listOf("Potato", "Wood", "Stone")
val WeaponStrengthList = listOf(0, 1, 2)
val NoWpnButtons = 3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BattleStats.text = "Welcome To the Battle Calculator\n\nPlease choose your weapons!"

        //Initiate the list of weapons

        UpdateWpnText()

        binding.weapon1.setOnClickListener { AddWeapon(1) }
        binding.weapon2.setOnClickListener { AddWeapon(2) }
        binding.weapon3.setOnClickListener { AddWeapon(3) }

        binding.Reset.setOnClickListener { ResetWeapons() }

        binding.CalculateStats.setOnClickListener { CalulateStats() }
    }

    private fun ResetWeapons() {
        for (x in 0..(NoWpnButtons - 1)) {
            NoWeaponsList[x] = 0
        }
        binding.BattleStats.text = "Welcome To the Battle Calculator\n\nPlease choose your weapons!"
        UpdateWpnText()
    }

    private fun AddWeapon(WpnNo: Int) {
        NoWeaponsList[WpnNo - 1]++
        UpdateWpnText()
    }

    private fun CalulateStats() {
        //Sum the total number of wepons
        val TotalNoWeapons = NoWeaponsList.sum()
        var TotalAttack = 0
        for (x in 0..(NoWpnButtons - 1)) {
            TotalAttack += NoWeaponsList[x] * WeaponStrengthList[x]
        }
        val Str1 = "You have choosen ${TotalNoWeapons} weapons"
        val Str2 = "The total strength is ${TotalAttack}"



        binding.BattleStats.text = Str1 + "\n" + Str2
    }


    private fun UpdateWpnText() {
        binding.NoWpn1.text = NoWeaponsList[0].toString()
        binding.NoWpn2.text = NoWeaponsList[1].toString()
        binding.NoWpn3.text = NoWeaponsList[2].toString()
    }
}

fun <T> generate(size: Int, value: T): MutableList<T> {
    return (0 until size).map { value }.toMutableList()
}

fun getProbList(WpnList: List<Int>, WpnPowList: List<Int>, NumCards: Int): List<Double> {

    if (NumCards > 1) { throw java.lang.Exception("Only one card implemented yet") }
    if (WpnList.size != WpnPowList.size) { throw java.lang.Exception("Lists of unequal length") }

    /*If we can only drw one cards, the maxium value is given by the most powerful weapon card */
    var LargestVal = 0
    for (n in 0..(WpnList.size - 1)) {
        if (WpnList[n] > 0) {
            if (WpnPowList[n] > LargestVal) {
                LargestVal = WpnPowList[n]
            }
        }
    }
    // Craete the list of apropriate length
    val ReturnList: MutableList<Double> = generate(LargestVal+1, 0.0)
    // Lookp through the weapons list and find the probability for each weapon
    val TotalNoWeapons = WpnList.sum()
    for (n in 0..(WpnList.size - 1)) {
        val Prob = (1.0 *WpnList[n])/(TotalNoWeapons)
        if (Prob != 0.0) {
            ReturnList[WpnPowList[n]] += Prob
        }
    }

    return ReturnList
}