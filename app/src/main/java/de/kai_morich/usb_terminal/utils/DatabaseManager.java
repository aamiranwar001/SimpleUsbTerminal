package de.kai_morich.usb_terminal.utils;

import android.database.Cursor;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

import de.kai_morich.usb_terminal.App;

public class DatabaseManager {

    public static List<Integer> getDistinctTrialDataIdFromSignals(App app, long trialId) {
        List<Integer> trialDataIds = new ArrayList<>();
        Database database = app.getDaoSession().getDatabase();
        Cursor cursor = database.rawQuery("SELECT DISTINCT TRIAL_DATA_ID FROM signals WHERE EXISTS (SELECT * FROM trial_data WHERE trial_data.id = signals.TRIAL_DATA_ID AND trial_data.TRIAL_ID = ?)", new String[] {String.valueOf(trialId)});
        while (cursor.moveToNext()) {
            trialDataIds.add(cursor.getInt(0));
        }
        cursor.close();
        return trialDataIds;
    }
}
