package health;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Server instance;
    private static Map<String, User> userMap = new HashMap<>();
    public static Map<String, DailyData> dataMap = new HashMap<>();

    private Server() {}

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public static boolean registerUser(User user) {
        if (userMap.containsKey(user.getUsername())) {
            return false; // 이미 등록된 ID인 경우 false 반환
        }
        userMap.put(user.getUsername(), user);
        return true;
    }

    public static User authenticateUser(String username, String password) {
        User user = userMap.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static void saveData(int calories, int exercise) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월dd일");
        Date time = new Date();
        String date = format1.format(time);

        dataMap.put(date, new DailyData(calories, exercise));
    }

    static class DailyData {
        private int calories;
        private int exercise;

        public DailyData(int calories, int exercise) {
            this.calories = calories;
            this.exercise = exercise;
        }

        public int getCalories() {
            return calories;
        }

        public int getExercise() {
            return exercise;
        }
    }
}