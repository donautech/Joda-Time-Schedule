package org.joda.time;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ScheduleTest {

    @Test
    public void single_intersect() {
        final Schedule firstSchedule = new Schedule(new ArrayList<Interval>() {{
            add(Interval.parse("2020-01-01T13:00:00Z/2020-01-01T14:00:00Z"));
            add(Interval.parse("2020-01-01T15:00:00Z/2020-01-01T16:00:00Z"));
        }});
        final Schedule secondSchedule = new Schedule(new ArrayList<Interval>() {{
            add(Interval.parse("2020-01-01T13:30:00Z/2020-01-01T14:00:00Z"));
        }});

        final Schedule intersectedSchedule = firstSchedule.intersect(secondSchedule);
        assertEquals(1, intersectedSchedule.size());

    }

    @Test
    public void double_intersect() {
        final Schedule firstSchedule = new Schedule(new ArrayList<Interval>() {{
            add(Interval.parse("2020-01-01T13:00:00Z/2020-01-01T14:00:00Z"));
            add(Interval.parse("2020-01-01T15:00:00Z/2020-01-01T16:00:00Z"));
        }});
        final Schedule secondSchedule = new Schedule(new ArrayList<Interval>() {{
            add(Interval.parse("2020-01-01T13:30:00Z/2020-01-01T15:30:00Z"));
        }});

        final Schedule intersectedSchedule = firstSchedule.intersect(secondSchedule);
        assertEquals(2, intersectedSchedule.size());

    }

    @Test
    public void double_intersect_reverse() {
        final Schedule secondSchedule = new Schedule(new ArrayList<Interval>() {{
            add(Interval.parse("2020-01-01T13:00:00Z/2020-01-01T14:00:00Z"));
            add(Interval.parse("2020-01-01T15:00:00Z/2020-01-01T16:00:00Z"));
        }});
        final Schedule firstSchedule = new Schedule(new ArrayList<Interval>() {{
            add(Interval.parse("2020-01-01T13:30:00Z/2020-01-01T15:30:00Z"));
        }});

        final Schedule intersectedSchedule = firstSchedule.intersect(secondSchedule);
        assertEquals(2, intersectedSchedule.size());
    }

    @Test
    public void after_now() {
        final Schedule schedule = new Schedule(new ArrayList<Interval>() {{
            add(new Interval(DateTime.now().minusHours(3), DateTime.now().minusHours(2)));
            add(new Interval(DateTime.now().plusHours(2), DateTime.now().plusHours(3)));
        }});
        final Schedule afterNowSchedule = schedule.afterNow();
        assertEquals(1, afterNowSchedule.size());
    }
}