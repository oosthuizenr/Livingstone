package com.livingstoneapp.interactors;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;

import com.livingstoneapp.models.ActivityInfoInternal;
import com.livingstoneapp.utils.Utils;

/**
 * Created by renier on 2/17/2016.
 */
public class ActivityInteractorImpl implements IActivityInteractor {
    private Context mContext;

    public ActivityInteractorImpl(Context context) {
        mContext = context;
    }


    @Override
    public Observable<ArrayList<ActivityInfoInternal>> getActivities(String packageName) {
        return Observable.defer(() -> Observable.create(subscriber -> {
            try {
                ArrayList<ActivityInfoInternal> toReturn = new ArrayList<>();

                PackageManager packageManager = mContext.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);

                if (packageInfo.activities != null) {

                    ArrayList<String> mainActivities = new ArrayList<>();

                    //Get the main activities for this app
                    Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                    mainIntent.setPackage(packageName);
                    List<ResolveInfo> actList = packageManager.queryIntentActivities(mainIntent, PackageManager.MATCH_ALL);
                    for (ResolveInfo ri : actList) {
                        mainActivities.add(ri.activityInfo.name);
                    }

                    //Get all the activities categories. This is cheaper than doing it for each activity in the loop below.
                    ArrayList<String> launcherActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_LAUNCHER, true);
                    ArrayList<String> defaultActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_DEFAULT, false);
                    ArrayList<String> browsableActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_BROWSABLE, false);
                    ArrayList<String> tabActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_TAB, false);
                    ArrayList<String> alternativeActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_ALTERNATIVE, false);
                    ArrayList<String> selectedAlternativeActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_SELECTED_ALTERNATIVE, false);
                    ArrayList<String> infoActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_INFO, false);
                    ArrayList<String> homeActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_HOME, false);
                    ArrayList<String> preferenceActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_PREFERENCE, false);
                    ArrayList<String> testActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_TEST, false);
                    ArrayList<String> carDockActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_CAR_DOCK, false);
                    ArrayList<String> deskDockActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_DESK_DOCK, false);
                    ArrayList<String> leDeskDockActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_LE_DESK_DOCK, false);
                    ArrayList<String> heDeskDockActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_HE_DESK_DOCK, false);
                    ArrayList<String> carModeActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_CAR_MODE, false);
                    ArrayList<String> appMarketActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_MARKET, false);
                    ArrayList<String> browserActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_BROWSER, true);
                    ArrayList<String> calculatorActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_CALCULATOR, true);
                    ArrayList<String> calendarActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_CALENDAR, true);
                    ArrayList<String> contactsActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_CONTACTS, true);
                    ArrayList<String> emailActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_EMAIL, true);
                    ArrayList<String> galleryActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_GALLERY, true);
                    ArrayList<String> mapsActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_MAPS, true);
                    ArrayList<String> messagingActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_MESSAGING, true);
                    ArrayList<String> musicActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_APP_MUSIC, true);
                    ArrayList<String> developmentPreferenceActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_DEVELOPMENT_PREFERENCE, false);
                    ArrayList<String> embedActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_EMBED, false);
                    ArrayList<String> frameworkInstrumentationTestActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST, false);
                    ArrayList<String> leanbackLauncherActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_LEANBACK_LAUNCHER, false);
                    ArrayList<String> monkeyActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_MONKEY, false);
                    ArrayList<String> openableActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_OPENABLE, false);
                    ArrayList<String> sampleCodeActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_SAMPLE_CODE, false);
                    ArrayList<String> unitTestActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_UNIT_TEST, false);
                    ArrayList<String> voiceActivities = getCategoryActivities(packageManager, packageName, Intent.CATEGORY_VOICE, false);


                    for (ActivityInfo info : packageInfo.activities) {
                        String documentLaunchMode = null;
                        String launchMode = null;
                        String persistableMode = null;
                        String screenOrientation = null;
                        ArrayList<String> softInputMode = new ArrayList<>();
                        boolean isLauncher = launcherActivities.contains(info.name);
                        boolean isMain = mainActivities.contains(info.name);
                        ArrayList<String> configChanges = new ArrayList<>();
                        ArrayList<String> flags = new ArrayList<>();
                        ArrayList<String> categories = new ArrayList<>();


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            switch (info.documentLaunchMode) {
                                case ActivityInfo.DOCUMENT_LAUNCH_ALWAYS:
                                    documentLaunchMode = "DOCUMENT_LAUNCH_ALWAYS";
                                    break;
                                case ActivityInfo.DOCUMENT_LAUNCH_INTO_EXISTING:
                                    documentLaunchMode = "DOCUMENT_LAUNCH_INTO_EXISTING";
                                    break;
                                case ActivityInfo.DOCUMENT_LAUNCH_NEVER:
                                    documentLaunchMode = "DOCUMENT_LAUNCH_NEVER";
                                    break;
                                case ActivityInfo.DOCUMENT_LAUNCH_NONE:
                                    documentLaunchMode = "DOCUMENT_LAUNCH_NONE";
                                    break;
                            }
                        }


                        switch (info.launchMode) {
                            case ActivityInfo.LAUNCH_MULTIPLE:
                                launchMode = "LAUNCH_MULTIPLE";
                                break;
                            case ActivityInfo.LAUNCH_SINGLE_INSTANCE:
                                launchMode = "LAUNCH_SINGLE_INSTANCE";
                                break;
                            case ActivityInfo.LAUNCH_SINGLE_TASK:
                                launchMode = "LAUNCH_SINGLE_TASK";
                                break;
                            case ActivityInfo.LAUNCH_SINGLE_TOP:
                                launchMode = "LAUNCH_SINGLE_TOP";
                                break;
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            switch (info.persistableMode) {
                                case ActivityInfo.PERSIST_ACROSS_REBOOTS:
                                    persistableMode = "PERSIST_ACROSS_REBOOTS";
                                    break;
                                case ActivityInfo.PERSIST_NEVER:
                                    persistableMode = "PERSIST_NEVER";
                                    break;
                                case ActivityInfo.PERSIST_ROOT_ONLY:
                                    persistableMode = "PERSIST_ROOT_ONLY";
                                    break;
                            }
                        }

                        switch (info.screenOrientation) {
                            case ActivityInfo.SCREEN_ORIENTATION_BEHIND:
                                screenOrientation = "SCREEN_ORIENTATION_BEHIND";
                            case ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR:
                                screenOrientation = "SCREEN_ORIENTATION_FULL_SENSOR";
                            case ActivityInfo.SCREEN_ORIENTATION_FULL_USER:
                                screenOrientation = "SCREEN_ORIENTATION_FULL_USER";
                            case ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:
                                screenOrientation = "SCREEN_ORIENTATION_LANDSCAPE";
                            case ActivityInfo.SCREEN_ORIENTATION_LOCKED:
                                screenOrientation = "SCREEN_ORIENTATION_LOCKED";
                            case ActivityInfo.SCREEN_ORIENTATION_NOSENSOR:
                                screenOrientation = "SCREEN_ORIENTATION_NOSENSOR";
                            case ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                                screenOrientation = "SCREEN_ORIENTATION_PORTRAIT";
                            case ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE:
                                screenOrientation = "SCREEN_ORIENTATION_REVERSE_LANDSCAPE";
                            case ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT:
                                screenOrientation = "SCREEN_ORIENTATION_REVERSE_PORTRAIT";
                            case ActivityInfo.SCREEN_ORIENTATION_SENSOR:
                                screenOrientation = "SCREEN_ORIENTATION_SENSOR";
                            case ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE:
                                screenOrientation = "SCREEN_ORIENTATION_SENSOR_LANDSCAPE";
                            case ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT:
                                screenOrientation = "SCREEN_ORIENTATION_SENSOR_PORTRAIT";
                            case ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED:
                                screenOrientation = "SCREEN_ORIENTATION_UNSPECIFIED";
                            case ActivityInfo.SCREEN_ORIENTATION_USER:
                                screenOrientation = "SCREEN_ORIENTATION_USER";
                            case ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE:
                                screenOrientation = "SCREEN_ORIENTATION_USER_LANDSCAPE";
                            case ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT:
                                screenOrientation = "SCREEN_ORIENTATION_USER_PORTRAIT";
                                break;
                        }

                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED))
                            softInputMode.add("SOFT_INPUT_STATE_UNSPECIFIED");
                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED))
                            softInputMode.add("SOFT_INPUT_STATE_UNCHANGED");
                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN))
                            softInputMode.add("SOFT_INPUT_STATE_HIDDEN");
                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE))
                            softInputMode.add("SOFT_INPUT_STATE_ALWAYS_VISIBLE");
                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE))
                            softInputMode.add("SOFT_INPUT_STATE_VISIBLE");
                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED))
                            softInputMode.add("SOFT_INPUT_ADJUST_UNSPECIFIED");
                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE))
                            softInputMode.add("SOFT_INPUT_ADJUST_RESIZE");
                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN))
                            softInputMode.add("SOFT_INPUT_ADJUST_PAN");
                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN))
                            softInputMode.add("SOFT_INPUT_STATE_ALWAYS_HIDDEN");
                        if (Utils.hasFlag(info.softInputMode, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING))
                            softInputMode.add("SOFT_INPUT_ADJUST_NOTHING");

                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_DENSITY))
                            configChanges.add("CONFIG_DENSITY");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_FONT_SCALE))
                            configChanges.add("CONFIG_FONT_SCALE");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_KEYBOARD))
                            configChanges.add("CONFIG_KEYBOARD");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_KEYBOARD_HIDDEN))
                            configChanges.add("CONFIG_KEYBOARD_HIDDEN");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_LAYOUT_DIRECTION))
                            configChanges.add("CONFIG_LAYOUT_DIRECTION");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_LOCALE))
                            configChanges.add("CONFIG_LOCALE");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_MCC))
                            configChanges.add("CONFIG_MCC");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_MNC))
                            configChanges.add("CONFIG_MNC");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_NAVIGATION))
                            configChanges.add("CONFIG_NAVIGATION");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_ORIENTATION))
                            configChanges.add("CONFIG_ORIENTATION");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_SCREEN_LAYOUT))
                            configChanges.add("CONFIG_SCREEN_LAYOUT");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_SCREEN_SIZE))
                            configChanges.add("CONFIG_SCREEN_SIZE");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_SMALLEST_SCREEN_SIZE))
                            configChanges.add("CONFIG_SMALLEST_SCREEN_SIZE");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_TOUCHSCREEN))
                            configChanges.add("CONFIG_TOUCHSCREEN");
                        if (Utils.hasFlag(info.configChanges, ActivityInfo.CONFIG_UI_MODE))
                            configChanges.add("CONFIG_UI_MODE");

                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_ALLOW_TASK_REPARENTING))
                            flags.add("FLAG_ALLOW_TASK_REPARENTING");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_ALWAYS_RETAIN_TASK_STATE))
                            flags.add("FLAG_ALWAYS_RETAIN_TASK_STATE");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_AUTO_REMOVE_FROM_RECENTS))
                            flags.add("FLAG_AUTO_REMOVE_FROM_RECENTS");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_CLEAR_TASK_ON_LAUNCH))
                            flags.add("FLAG_CLEAR_TASK_ON_LAUNCH");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_EXCLUDE_FROM_RECENTS))
                            flags.add("FLAG_EXCLUDE_FROM_RECENTS");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_FINISH_ON_CLOSE_SYSTEM_DIALOGS))
                            flags.add("FLAG_FINISH_ON_CLOSE_SYSTEM_DIALOGS");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_FINISH_ON_TASK_LAUNCH))
                            flags.add("FLAG_FINISH_ON_TASK_LAUNCH");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_HARDWARE_ACCELERATED))
                            flags.add("FLAG_HARDWARE_ACCELERATED");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_IMMERSIVE))
                            flags.add("FLAG_IMMERSIVE");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_MULTIPROCESS))
                            flags.add("FLAG_MULTIPROCESS");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_NO_HISTORY))
                            flags.add("FLAG_NO_HISTORY");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_RELINQUISH_TASK_IDENTITY))
                            flags.add("FLAG_RELINQUISH_TASK_IDENTITY");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_RESUME_WHILE_PAUSING))
                            flags.add("FLAG_RESUME_WHILE_PAUSING");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_SINGLE_USER))
                            flags.add("FLAG_SINGLE_USER");
                        if (Utils.hasFlag(info.flags, ActivityInfo.FLAG_STATE_NOT_NEEDED))
                            flags.add("FLAG_STATE_NOT_NEEDED");

                        if (defaultActivities.contains(info.name))
                            categories.add("CATEGORY_DEFAULT");
                        if (browsableActivities.contains(info.name))
                            categories.add("CATEGORY_BROWSABLE");
                        if (tabActivities.contains(info.name))
                            categories.add("CATEGORY_TAB");
                        if (alternativeActivities.contains(info.name))
                            categories.add("CATEGORY_ALTERNATIVE");
                        if (selectedAlternativeActivities.contains(info.name))
                            categories.add("CATEGORY_SELECTED_ALTERNATIVE");
                        if (launcherActivities.contains(info.name))
                            categories.add("CATEGORY_LAUNCHER");
                        if (infoActivities.contains(info.name))
                            categories.add("CATEGORY_INFO");
                        if (homeActivities.contains(info.name))
                            categories.add("CATEGORY_HOME");
                        if (preferenceActivities.contains(info.name))
                            categories.add("CATEGORY_PREFERENCE");
                        if (testActivities.contains(info.name))
                            categories.add("CATEGORY_TEST");
                        if (carDockActivities.contains(info.name))
                            categories.add("CATEGORY_CAR_DOCK");
                        if (deskDockActivities.contains(info.name))
                            categories.add("CATEGORY_DESK_DOCK");
                        if (leDeskDockActivities.contains(info.name))
                            categories.add("CATEGORY_LE_DESK_DOCK");
                        if (heDeskDockActivities.contains(info.name))
                            categories.add("CATEGORY_HE_DESK_DOCK");
                        if (carModeActivities.contains(info.name))
                            categories.add("CATEGORY_CAR_MODE");
                        if (appMarketActivities.contains(info.name))
                            categories.add("CATEGORY_APP_MARKET");
                        if (browserActivities.contains(info.name))
                            categories.add("CATEGORY_APP_BROWSER");
                        if (calculatorActivities.contains(info.name))
                            categories.add("CATEGORY_APP_CALCULATOR");
                        if (calendarActivities.contains(info.name))
                            categories.add("CATEGORY_APP_CALENDAR");
                        if (contactsActivities.contains(info.name))
                            categories.add("CATEGORY_APP_CONTACTS");
                        if (emailActivities.contains(info.name))
                            categories.add("CATEGORY_APP_EMAIL");
                        if (galleryActivities.contains(info.name))
                            categories.add("CATEGORY_APP_GALLERY");
                        if (mapsActivities.contains(info.name))
                            categories.add("CATEGORY_APP_MAPS");
                        if (messagingActivities.contains(info.name))
                            categories.add("CATEGORY_APP_MESSAGING");
                        if (musicActivities.contains(info.name))
                            categories.add("CATEGORY_APP_MUSIC");
                        if (developmentPreferenceActivities.contains(info.name))
                            categories.add("CATEGORY_DEVELOPMENT_PREFERENCE");
                        if (embedActivities.contains(info.name))
                            categories.add("CATEGORY_EMBED");
                        if (frameworkInstrumentationTestActivities.contains(info.name))
                            categories.add("CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST");
                        if (leanbackLauncherActivities.contains(info.name))
                            categories.add("CATEGORY_LEANBACK_LAUNCHER");
                        if (monkeyActivities.contains(info.name))
                            categories.add("CATEGORY_MONKEY");
                        if (openableActivities.contains(info.name))
                            categories.add("CATEGORY_OPENABLE");
                        if (sampleCodeActivities.contains(info.name))
                            categories.add("CATEGORY_SAMPLE_CODE");
                        if (unitTestActivities.contains(info.name))
                            categories.add("CATEGORY_UNIT_TEST");
                        if (voiceActivities.contains(info.name))
                            categories.add("CATEGORY_VOICE");

                        int maxRecents = -1;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            maxRecents = info.maxRecents;
                        }

                        String parentActivityName = "";
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            parentActivityName = info.parentActivityName;
                        }

                        toReturn.add(new ActivityInfoInternal(
                                info.name.startsWith(packageName) == true ? info.name.replace(packageName, "") : info.name,
                                info.enabled,
                                info.exported,
                                info.processName,
                                configChanges,
                                isLauncher,
                                isMain,
                                documentLaunchMode,
                                flags,
                                launchMode,
                                maxRecents,
                                parentActivityName,
                                info.permission,
                                persistableMode,
                                screenOrientation,
                                softInputMode,
                                info.targetActivity,
                                info.taskAffinity,
                                categories
                        ));
                    }
                }

                Collections.sort(toReturn);

                subscriber.onNext(toReturn);

                subscriber.onCompleted();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
        }));
    }

    private ArrayList<String> getCategoryActivities(PackageManager pm, String packageName, String category, boolean withMain) {
        ArrayList<String> toReturn = new ArrayList<>();

        Intent mainIntent;

        if (withMain) {
            mainIntent = new Intent(Intent.ACTION_MAIN, null);
        } else {
            mainIntent = new Intent();
        }

        mainIntent.addCategory(category);
        mainIntent.setPackage(packageName);

        List<ResolveInfo> actList = pm.queryIntentActivities(mainIntent, 0);
        for (ResolveInfo ri : actList) {
            toReturn.add(ri.activityInfo.name);
        }

        return toReturn;
    }
}
