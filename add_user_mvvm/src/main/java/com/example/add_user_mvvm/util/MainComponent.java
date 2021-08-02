package com.example.add_user_mvvm.util;

import com.example.add_user_mvvm.model.UserManager;
import com.example.add_user_mvvm.view.AddUser;
import com.example.add_user_mvvm.view.MainActivity;
import com.example.add_user_mvvm.viewmodel.UserArrayListViewModel;
import com.example.add_user_mvvm.viewmodel.UserViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
//@Component(modules = {AndroidSupportInjectionModule.class, MainModule.class, BuildersModule.class})
@Component(modules = { MainModule.class})
public interface MainComponent {

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        Builder application(MainApp mainApp);
//        MainComponent build();
//    }

    //MainModule mainModule();
    void inject(MainActivity mainActivity);
    void inject(UserManager userManager);
    void inject(UseFile useFile);
    void inject(UserArrayListViewModel userArrayListViewModel);
    void inject(UserViewModel userViewModel);
    void inject(AddUser addUser);
}