package com.example.biomescalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biomescalculator.databinding.ActivityMainBinding
import android.util.Log

//Variable for binding
lateinit var binding: ActivityMainBinding

private const val TAG = "MainActivity"

val NoWpnButtons = 9
val NoWeaponsList = generate(NoWpnButtons, 0)
val WeaponStrengthList = listOf(0, 1, 2, 2, 2, 2, 3, 4, 6)


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Setting the inital variables")

        binding.BattleStats.text = "Welcome To the Battle Calculator\n\nPlease choose your weapons!"

        //Initiate the list of weapons
        ResetWeapons()
        UpdateWpnText()

        SetClickListenders()
    }

    private fun SetClickListenders() {
        binding.weapon1.setOnClickListener { AddWeapon(1) }
        binding.weapon2.setOnClickListener { AddWeapon(2) }
        binding.weapon3.setOnClickListener { AddWeapon(3) }
        binding.weapon4.setOnClickListener { AddWeapon(4) }
        binding.weapon5.setOnClickListener { AddWeapon(5) }
        binding.weapon6.setOnClickListener { AddWeapon(6) }
        binding.weapon7.setOnClickListener { AddWeapon(7) }
        binding.weapon8.setOnClickListener { AddWeapon(8) }
        binding.weapon9.setOnClickListener { AddWeapon(9) }

        binding.Reset.setOnClickListener { ResetWeapons() }
        binding.CalculateStats.setOnClickListener { calculateStats() }
    }


    private fun ResetWeapons() {
        for (x in 0..(NoWpnButtons - 1)) {
            NoWeaponsList[x] = 0
        }
        //Set the standard weapons
        NoWeaponsList[0] = 3
        NoWeaponsList[1] = 1
        NoWeaponsList[2] = 1
        binding.BattleStats.text = "Welcome To the Battle Calculator\n\nPlease choose your weapons!"
        UpdateWpnText()
    }

    private fun AddWeapon(WpnNo: Int) {
        NoWeaponsList[WpnNo - 1]++
        UpdateWpnText()
    }

    private fun calculateStats() {
        //Sum the total number of wepons
        val TotalNoWeapons = NoWeaponsList.sum()
        var NumCards = 3

        val Str1 = "You have choosen ${TotalNoWeapons} weapons"

        if (TotalNoWeapons < NumCards) {
            binding.BattleStats.text = "Too few weapons. You need at least ${NumCards}"
            return
        }
        val ProbList = getProbList(NoWeaponsList, WeaponStrengthList, NumCards)
        val ReverseCumList =
            generate(ProbList.size, 0.0) //Gernate a list to store the cumulate prob
        var Average = 0.0
        val Str3 = StringBuilder()
        for (n in (ProbList.size - 1) downTo 0) {
            if (n == ((ProbList.size - 1))) {
                ReverseCumList[n] = ProbList[n]
            } else {
                ReverseCumList[n] = ReverseCumList[n + 1] + ProbList[n]
            }
        }
        for (n in 0..(ProbList.size - 1)) {
            Str3.append(
                "${n}:     " + "%.0f".format(ProbList[n] * 100) + "%         " + "%.0f".format(
                    ReverseCumList[n] * 100
                ) + "%\n"
            )
            Average += ProbList[n] * n
        }

        var Str4 = "Average attack is " + "%.1f".format(Average)

        binding.BattleStats.text = Str1 + "\n" + Str4 + "\n" + Str3.toString()
    }


    private fun UpdateWpnText() {
        binding.NoWpn1.text = NoWeaponsList[0].toString()
        binding.NoWpn2.text = NoWeaponsList[1].toString()
        binding.NoWpn3.text = NoWeaponsList[2].toString()
        binding.NoWpn4.text = NoWeaponsList[3].toString()
        binding.NoWpn5.text = NoWeaponsList[4].toString()
        binding.NoWpn6.text = NoWeaponsList[5].toString()
        binding.NoWpn7.text = NoWeaponsList[6].toString()
        binding.NoWpn8.text = NoWeaponsList[7].toString()
        binding.NoWpn9.text = NoWeaponsList[8].toString()

    }
}

fun <T> generate(size: Int, value: T): MutableList<T> {
    return (0 until size).map { value }.toMutableList()
}

fun getProbList(
    WpnList: List<Int>, WpnPowList: List<Int>, NumCards: Int,
    IsBowList: List<Boolean> = generate(WpnList.size, false)
): MutableList<Double> {

    if (NumCards < 0) {
        throw java.lang.Exception("Cannot draw negative amount of cards")
    }
    if (NumCards == 0) {
        return generate(1, 1.0)
    }

    if (NumCards > WpnList.sum()) {
        throw java.lang.Exception("Drawing more cards than weapons")
    }
    if (WpnList.size != WpnPowList.size) {
        throw java.lang.Exception("Lists of unequal length")
    }

    /*If we can only drw one cards, the maxium value is given by the most powerful weapon card */
    var LargestVal = 0
    for (n in 0..(WpnList.size - 1)) {
        if (WpnList[n] > 0) {
            if (WpnPowList[n] > LargestVal) {
                LargestVal = WpnPowList[n]
            }
        }
    }
    // Create the list of aproriate length
    var ReturnList: MutableList<Double> = generate(LargestVal + 1, 0.0)
    // Lookp through the weapons list and find the probability for each weapon
    val TotalNoWeapons = WpnList.sum()
    for (n in 0..(WpnList.size - 1)) {
        val Prob = (1.0 * WpnList[n]) / (TotalNoWeapons)
        if (Prob != 0.0) {
            val Power = WpnPowList[n]

            val NewWpnList = WpnList.toMutableList()
            NewWpnList[n] -= 1 //Reduce the new list by one
            //If the weapon has the bow property, then draw one extra card
            var NewNumCards = NumCards - 1
            if (IsBowList[n]) {
                NewNumCards++
            }
            val NewProbList = getProbList(NewWpnList, WpnPowList, NewNumCards)
            ReturnList = mergeReturnList(ReturnList, NewProbList, Prob, Power)


        }
    }

    return ReturnList
}

// Merges mutable lsits with a possible shift and scale
fun mergeReturnList(
    TargetList: MutableList<Double>,
    ListAdd: MutableList<Double>,
    scale: Double, IndxShift: Int
): MutableList<Double> {
    if (TargetList.size < ListAdd.size + IndxShift) {
        //Extend the list so that they are of matching lengths
        val ExtraNeed = ListAdd.size + IndxShift - TargetList.size
        val ExtraVals = generate(ExtraNeed, 0.0)
        TargetList.addAll(ExtraVals)
    }

    //Large enough continue
    for (n in 0..(ListAdd.size - 1)) {
        TargetList[n + IndxShift] += ListAdd[n] * scale
    }
    return TargetList
}

