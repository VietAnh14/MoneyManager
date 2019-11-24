package vianh.nva.moneymanager.ui.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import vianh.nva.moneymanager.R;
import vianh.nva.moneymanager.data.entity.Money;

public class CalendarFragment extends Fragment {
    private Calendar calendar = Calendar.getInstance();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private CalendarView calendarView;
    private CalendarViewModel calendarViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = root.findViewById(R.id.calendarView);
//        List<EventDay> events = new ArrayList<>();
//        Calendar calendar2 = Calendar.getInstance();
//        events.add(new EventDay(calendar2, R.drawable.dot));
//        Calendar calendar3 = Calendar.getInstance();
//        events.add(new EventDay(calendar3, R.drawable.dot));
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.add(Calendar.DAY_OF_MONTH, 2);
//        events.add(new EventDay(calendar1, R.drawable.dot));
//        calendarView.setEvents(events);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        compositeDisposable.add(calendarViewModel.getListMoneyByMonth(calendar.get(Calendar.MONTH) + 1)
                .subscribeOn(Schedulers.io())
                .map(monies -> {
                    List<EventDay> events = new ArrayList<>();
                    for (Money money : monies) {
                        Calendar date = Calendar.getInstance();
                        date.setTime(money.getDate());
                        events.add(new EventDay(date, R.drawable.ic_calendar));
                    }
                    return events;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        events -> {
//                            Log.d("success", String.valueOf(listMoney.size()));
//                            List<EventDay> listEvent = new ArrayList<>();
//                            for (Money money : listMoney) {
//                                Calendar date = Calendar.getInstance();
//                                date.setTime(money.getDate());
//                                listEvent.add(new EventDay(date, R.drawable.ic_calendar));
//                            }
                            calendarView.setEvents(events);
                        },

                        throwable ->Log.d("err", "err", throwable)
                )
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}