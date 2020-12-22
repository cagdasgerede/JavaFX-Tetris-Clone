package com.quirko.logic.achievements;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AchievementFileIO {

    private File file;

    ArrayList<String> users;
    ArrayList<String> userAchievements;

    public AchievementFileIO() {
        String userDirectory = new File("").getAbsolutePath();

        try {
            file = new File(userDirectory + "\\src\\main\\resources\\user_achievements.txt");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        users = new ArrayList<String>();
        userAchievements = new ArrayList<String>();
        loadAchievementData();
    }

    private void loadAchievementData() {
        int line = 0;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (line % 4 == 0)
                    users.add(data);
                else
                    userAchievements.add(data);
                line++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAchievements(String username, AchievementManager achievements) {
        if (users.contains(username)) {
            int index = -1;
            for (int i = 0; i < users.size(); i++) {
                if (username.equals(users.get(i))) {
                    index = i;
                    break;
                }
            }
            userAchievements.set(3 * index, achievements.totalCleared.getLastCompleted() + "");
            userAchievements.set(3 * index + 1, achievements.clearedAtOnce.getLastCompleted() + "");
            userAchievements.set(3 * index + 2, achievements.scored.getLastCompleted() + "");
        }

        try {
            FileWriter writer = new FileWriter(file, false);

            for (int i = 0; i < users.size(); i++) {
                if (i != 0)
                    writer.write("\n");
                writer.write(users.get(i) + "\n");
                for (int j = i * 3; j <= i * 3 + 2; j++) {
                    if (j == i * 3 + 2)
                        writer.write(userAchievements.get(j).toString());
                    else
                        writer.write(userAchievements.get(j).toString() + "\n");
                }
            }
            if (!users.contains(username)) {
                writer.write("\n" + username + "\n");
                writer.write(achievements.totalCleared.getLastCompleted() + "\n");
                writer.write(achievements.clearedAtOnce.getLastCompleted() + "\n");
                writer.write(achievements.scored.getLastCompleted() + "");
            }
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean userExists(String username) {
        return users.contains(username);
    }

    public int[] getUserAchievements(String username) {
        int[] achievements = new int[3];
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i))) {
                index = i;
                break;
            }
        }
        achievements[0] = Integer.parseInt(userAchievements.get(3 * index));
        achievements[1] = Integer.parseInt(userAchievements.get(3 * index + 1));
        achievements[2] = Integer.parseInt(userAchievements.get(3 * index + 2));
        return achievements;
    }

}
