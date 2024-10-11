package Utils

import androidx.compose.ui.graphics.Color

class ColorUtils {
    companion object{
        fun getColorBasedOnInt(number : Int, step : Int) : Color {
            if(number.mod(6) == 0){
                return Color(step*number.mod(256),0,0)
            }else if(number.mod(6) == 1){
                return Color(step*number.mod(256),step*number.mod(256),0)
            }else if(number.mod(6) == 2){
                return Color(0,step*number.mod(256),0)
            }else if(number.mod(6) == 3){
                return Color(0,step*number.mod(256),step*number.mod(256))
            }else if(number.mod(6) == 4){
                return Color(0,0,step*number.mod(256))
            }else if(number.mod(6) == 5){
                return Color(step*number.mod(256),0,step*number.mod(256))
            }
            return Color(0,0,0)
        }

        fun getListOfColors(count : Int, step : Int) : MutableList<Color>{
            var result = mutableListOf<Color>()
            for(i in 1..count){
                result.add(getColorBasedOnInt(i,step))
            }
            return result
        }
    }
}