package edu.gatech.donatrix;

import android.app.Application;
import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import edu.gatech.donatrix.dao.Database;
import edu.gatech.donatrix.model.User;
import edu.gatech.donatrix.model.UserType;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.junit.Assert.*;

public class testEmailValid {
    private File file;
    private FileInputStream fis;
    private ObjectOutputStream os;

    @Before
    public void setUp() {
        try {
            file = File.createTempFile("temp", "db");
            fis = new FileInputStream(file);
            os = new ObjectOutputStream(new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadUsers() {
        try {
            HashMap<String, User> userMap = new HashMap<>();
            User user = new User("test@test.com");
            userMap.put("temp1", user);
            os.writeObject(userMap);

            Context context = new Application();
            Context spyContext = spy(context);
            doReturn(fis).when(spyContext).openFileInput("users.db");

            Database.getInstance(context).readFile("users.db", UserType.USER.getType(), context);

            assertEquals(Database.userMap.get("temp1").getEmail(), "test@test.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            os.close();
            fis.close();
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
