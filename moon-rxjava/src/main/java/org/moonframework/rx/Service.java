package org.moonframework.rx;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/2/10
 */
public class Service {

    public static void A() {
        Observable<User> x = Observable
                .just(1)
                .subscribeOn(Schedulers.io())
                .map(Service::user);

        Observable<UserProfile> y = Observable
                .just(1)
                .subscribeOn(Schedulers.io())
                .map(Service::profile);

        Observable<User> result = Observable.zip(x, y, (user, userProfile) -> {
            user.profile = userProfile;
            return user;
        });

        User user = result.blockingSingle();
//                .blockingSubscribe(user -> {
//                    System.out.println(user.id);
//                    System.out.println(user.profile.rank);
//                });
        System.out.println(user);

    }

    public static User user(int id) {
        System.out.println("user");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.id = id;
        user.username = "username";
        user.password = "password";
        return user;
    }

    public static UserProfile profile(int id) {
        System.out.println("profile");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserProfile profile = new UserProfile();
        profile.id = id;
        profile.sex = 1;
        profile.rank = 10;
        return profile;
    }

    private static class User {
        private long id;
        private String username;
        private String password;
        private UserProfile profile;
    }

    private static class UserProfile {
        private long id;
        private int sex;
        private int rank;
    }

    public static void main(String[] args) {
        A();
    }

}
