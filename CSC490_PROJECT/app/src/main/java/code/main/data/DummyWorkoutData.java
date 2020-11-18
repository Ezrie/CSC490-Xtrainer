package code.main.data;

public class DummyWorkoutData {

    public static final MuscleDataObject upperChest = new MuscleDataObject("Chest", "Upper Chest");
    public static final MuscleDataObject calves = new MuscleDataObject("Legs", "Calves");
    public static final MuscleDataObject biceps = new MuscleDataObject("Arms", "Biceps");
    public static final MuscleDataObject forearms = new MuscleDataObject("Arms", "Forearms");
    public static final MuscleDataObject lowerChest = new MuscleDataObject("Chest", "LowerChest");
    public static final MuscleDataObject hamstrings = new MuscleDataObject("Legs", "Hamstrings");

    public static final IsolationScheduleContainer.Days[] iso_schedule_mwf = new IsolationScheduleContainer.Days[]{IsolationScheduleContainer.Days.MONDAY, IsolationScheduleContainer.Days.WEDNESDAY, IsolationScheduleContainer.Days.FRIDAY};
    public static final IsolationScheduleContainer.Days[] iso_schedule_tth = new IsolationScheduleContainer.Days[]{IsolationScheduleContainer.Days.TUESDAY, IsolationScheduleContainer.Days.THURSDAY};
    public static final IsolationScheduleContainer.Days[] iso_schedule_sw = new IsolationScheduleContainer.Days[]{IsolationScheduleContainer.Days.SUNDAY, IsolationScheduleContainer.Days.WEDNESDAY};
    public static final IsolationScheduleContainer.Days[] iso_schedule_st = new IsolationScheduleContainer.Days[]{IsolationScheduleContainer.Days.SATURDAY, IsolationScheduleContainer.Days.TUESDAY};

    public static final PushPullScheduleContainer.Days[] pp_schedule_mwf = new PushPullScheduleContainer.Days[]{PushPullScheduleContainer.Days.MONDAY, PushPullScheduleContainer.Days.WEDNESDAY, PushPullScheduleContainer.Days.FRIDAY};
    public static final PushPullScheduleContainer.Days[] pp_schedule_tth = new PushPullScheduleContainer.Days[]{PushPullScheduleContainer.Days.TUESDAY, PushPullScheduleContainer.Days.THURSDAY};
    public static final PushPullScheduleContainer.Days[] pp_schedule_sw = new PushPullScheduleContainer.Days[]{PushPullScheduleContainer.Days.SUNDAY, PushPullScheduleContainer.Days.WEDNESDAY};
    public static final PushPullScheduleContainer.Days[] pp_schedule_st = new PushPullScheduleContainer.Days[]{PushPullScheduleContainer.Days.SATURDAY, PushPullScheduleContainer.Days.TUESDAY};

    public static final IsolationScheduleContainer iso_container1 = new IsolationScheduleContainer(iso_schedule_mwf, upperChest);
    public static final IsolationScheduleContainer iso_container2 = new IsolationScheduleContainer(iso_schedule_tth, calves);
    public static final IsolationScheduleContainer iso_container3 = new IsolationScheduleContainer(iso_schedule_sw, biceps);
    public static final IsolationScheduleContainer iso_container4 = new IsolationScheduleContainer(iso_schedule_st, forearms);
    public static final IsolationScheduleContainer iso_container5 = new IsolationScheduleContainer(iso_schedule_mwf, lowerChest);
    public static final IsolationScheduleContainer iso_container6 = new IsolationScheduleContainer(iso_schedule_tth, hamstrings);

    public static final PushPullScheduleContainer pp_container1 = new PushPullScheduleContainer(pp_schedule_st, PushPullScheduleContainer.ExerciseTypeEnum.PULL);
    public static final PushPullScheduleContainer pp_container2 = new PushPullScheduleContainer(pp_schedule_sw, PushPullScheduleContainer.ExerciseTypeEnum.PUSH);
    public static final PushPullScheduleContainer pp_container3 = new PushPullScheduleContainer(pp_schedule_tth, PushPullScheduleContainer.ExerciseTypeEnum.LEG);
    public static final PushPullScheduleContainer pp_container4 = new PushPullScheduleContainer(pp_schedule_mwf, PushPullScheduleContainer.ExerciseTypeEnum.PUSH);
    public static final PushPullScheduleContainer pp_container5 = new PushPullScheduleContainer(pp_schedule_st, PushPullScheduleContainer.ExerciseTypeEnum.PULL);
    public static final PushPullScheduleContainer pp_container6 = new PushPullScheduleContainer(pp_schedule_sw, PushPullScheduleContainer.ExerciseTypeEnum.LEG);

    public static final WorkoutDataObject dummy1 = new WorkoutDataObject(false, "Workout Example 1", "This is a description for Workout Example 1. The text wraps and doesn't overflow outside it's border. The text boxes also have scrolling! Check it out!", new IsolationScheduleContainer[]{iso_container1, iso_container2, iso_container3});
    public static final WorkoutDataObject dummy2 = new WorkoutDataObject(false, "Workout Example 2", "This is a description for Workout Example 2. The text wraps and doesn't overflow outside it's border. The text boxes also have scrolling! Check it out!", new IsolationScheduleContainer[]{iso_container3, iso_container4, iso_container5});
    public static final WorkoutDataObject dummy3 = new WorkoutDataObject(false, "Workout Example 3", "This is a description for Workout Example 3. The text wraps and doesn't overflow outside it's border. The text boxes also have scrolling! Check it out!", new IsolationScheduleContainer[]{iso_container4, iso_container5, iso_container6});
    public static final WorkoutDataObject dummy4 = new WorkoutDataObject(true, "Workout Example 4", "This is a description for Workout Example 4. The text wraps and doesn't overflow outside it's border. The text boxes also have scrolling! Check it out!", new PushPullScheduleContainer[]{pp_container1, pp_container2, pp_container3});
    public static final WorkoutDataObject dummy5 = new WorkoutDataObject(true, "Workout Example 5", "This is a description for Workout Example 5. The text wraps and doesn't overflow outside it's border. The text boxes also have scrolling! Check it out!", new PushPullScheduleContainer[]{pp_container3, pp_container4, pp_container5});
    public static final WorkoutDataObject dummy6 = new WorkoutDataObject(true, "Workout Example 6", "This is a description for Workout Example 6. The text wraps and doesn't overflow outside it's border. The text boxes also have scrolling! Check it out!", new PushPullScheduleContainer[]{pp_container4, pp_container5, pp_container6});

    //sample push/pull program
    //legs

    public static final String workout_1 = "Dumbbell Squat";
    public static final String desc_1 = "Have your feet hip-width apart.  Rest a dumbbell on each shoulder while still holding it with your hands to support most " +
            "of the weight. Keeping your body straight, lower yourself into the sitting position. Once your quadriceps are parallel to the " +
            "ground (you can go further to target the glutes more), push back up to the standing position.";
    public static final String workout_2 = "Leg Extension Machine";
    public static final String desc_2 = "In the starting position, have your legs set to be in a flexed state; have your calves pulled " +
            "slightly underneath your quadriceps.  Pushing with your quadriceps, extend your legs until " +
            "they reach their full range of motion. Then, lower your legs in a controlled manner to the " +
            "starting position.";
    public static final String workout_3 = "Cable Leg Extension";
    public static final String desc_3 = "Have a seat or bench in front of a cable machine with the pulley at ground level. Sitting down " +
            "with your back facing the machine, strap the cable to your ankle. While maintaining an " +
            "upward posture on on the bench, raise the strapped leg until it is leveled with the bench. " +
            "Lower the leg in a controlled manner for the next rep.";

    //pull exercises
    public static final String workout_4 = "Standing Barbell Upright Row ";
    public static final String desc_4 = "With the barbell in front of you, have a close grip on the center of the bar. " +
            "Using your shoulders, pull the bar to your chest. Make sure to have your " +
            "elbows above the bar at all times. Hold the position for 2 seconds then lower " +
            "the bar slowly.";
    public static final String workout_5 = "Bent-Over Row with Dumbbell";
    public static final String desc_5 = "Find a flat bench and sit on the edge of the seat. With a dumbbell in " +
            "each hand, lean forward to get your chest as close to your knees. " +
            "With your arms slightly bent, pull the dumbbells from your sides and " +
            "right up to shoulder height. Slowly lower the dumbbells to the starting " +
            "position for the next rep.";
    //push exercises
    public static final String workout_6 = "Inclined Push-up";
    public static final String desc_6 = "Find a bench for this exercise. Facing the bench, you want to have your legs height-distance " +
            "apart from the bench and lean forward so that you are in a push up position with your hands " +
            "on the sides of the bench. Keeping elbows tucked on the sides and downwards towards the chest, " +
            "push off the bench through your chest muscles. When the arms are fully extended with a slight bent, " +
            "lower yourself in a controlled manner to repeat the motion.";
    public static final String workout_7 = "Overhead Tricep Extension with Cable";
    public static final String desc_7 = "Set up a cable at hip or ground level. With a rope attached to the cable, stand and face " +
            "away from the cable.  Pull the cable behind your head with your upper arms aligned with " +
            "your head. Keeping the elbows locked, pull the rope forward by extending the arms, then " +
            "slowly move back to the starting position.";

    //isolation for arms
    //deltoids
    public static final String workout_8 = "Dumbbell Front Raise";
    public static final String desc_8 = "Have a dumbbell in each hand and while standing have your palms facing down. Raise " +
            "the dumbbells up right below the shoulder line, then lower the weights slowly in a controlled " +
            "manner to the front of your thighs.";

    //forearms
    public static final String workout_9 = "Dumbbell Wrist Twist";
    public static final String desc_9 = "Hold a dumbbell in each of your hands. With your hands towards at your sides, twist your " +
            "write clockwise and counterclockwise while keeping your elbows locked.  Make sure to " +
            "keep a firm grip while rotating the dumbbells to focus on your forearms more effectively.";

    //biceps
    public static final String workout_10 = "Barbell Bicep Curl";
    public static final String desc_10 = "Place both hands on the barbell shoulder-width apart and palms facing up. Make sure to " +
            "keep your arms at your side and elbows locked. It is also important that you maintain the " +
            "standing body posture as you don't want to use your back or shoulders in this exercise. " +
            "Slowly lift the bar up by just moving the forearms through the biceps until the weight is near " +
            "the chest. Slowly lower the bar for the next rep.";
    public static final String workout_11 = "Hammer Curls";
    public static final String desc_11 = "Have a dumbbell in each hand and at your sides. Your palms should be facing you. Make " +
            "sure to keep your arms at your side and elbows locked. It is also important that you " +
            "maintain the standing body posture as you don't want to use your back or shoulders in " +
            "this exercise. It might help to lean forward while doing this exercise. Slowly lift " +
            "the dumbbells up your sides by just moving the forearms through the biceps until " +
            "the weight is near the chest. Slowly lower the bar for the next rep.";
    public static final String workout_12 = "Dumbbell Reverse Curl";
    public static final String desc_12 = "Have a dumbbell in each hand and palms facing down. Make sure to keep your arms at your " +
            "side and elbows locked. It is also important that you maintain the standing body posture as " +
            "you don't want to use your back or shoulders in this exercise. Slowly lift the dumbbells up " +
            "by just moving the forearms through the biceps until the weight is near the chest. Slowly " +
            "lower the bar for the next rep.";

    //triceps
    public static final String workout_13 = "Overhead Tricep Extension with Cable";
    public static final String desc_13 = "Set up a cable at hip or ground level. With a rope attached to the cable, stand and face " +
            "away from the cable.  Pull the cable behind your head with your upper arms aligned with " +
            "your head. Keeping the elbows locked, pull the rope forward by extending the arms, then " +
            "slowly move back to the starting position.";
    public static final String workout_14 = "Rope pulldown";
    public static final String desc_14 = "Set a cable above your height with a rope attached. Facing the machine, grab the rope with " +
            "both hands and while keeping the elbows locked, pull the rope down until the arms are fully " +
            "extended. Then, slowly release your arms and return to the starting position in a controlled " +
            "manner.";

    public static final ExerciseDataObject pp_exercise1 = new ExerciseDataObject(calves, workout_1, desc_1, ExerciseDataObject.ExerciseTypeEnum.PUSH);
}
