package Utils

import java.util.*

class DateUtils {
    companion object{
        fun getTodayDateAtEnd() : Date {
            var calendar = Calendar.getInstance()
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23,59, 59)
            return calendar.time
        }

        fun get1WeekDateAtStart() : Date {
            var calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH,-7)
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0, 0)
            return calendar.time
        }

        fun get1MonthDateAtStart() : Date {
            var calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH,-1)
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0, 0)
            return calendar.time
        }

        fun get3MonthDateAtStart() : Date {
            var calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH,-3)
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0, 0)
            return calendar.time
        }
        fun get6MonthDateAtStart() : Date {
            var calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH,-6)
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0, 0)
            return calendar.time
        }

        fun get1YearDateAtStart() : Date {
            var calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR,-1)
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0, 0)
            return calendar.time
        }

        fun get2YearDateAtStart() : Date {
            var calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR,-2)
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0, 0)
            return calendar.time
        }

        fun get5YearDateAtStart() : Date {
            var calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR,-5)
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0, 0)
            return calendar.time
        }

        fun get10YearDateAtStart() : Date {
            var calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR,-10)
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,0, 0)
            return calendar.time
        }

    }
}