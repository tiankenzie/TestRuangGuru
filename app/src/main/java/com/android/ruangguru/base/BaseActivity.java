package com.android.ruangguru.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.ruangguru.R;
import com.android.ruangguru.base.di.component.ApplicationComponent;
import com.android.ruangguru.base.di.component.HasComponent;
import com.android.ruangguru.base.di.module.ActivityModule;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements HasComponent {
    private IncludeToolbar includeToolbar;

    @BindView(R.id.toolbar)
    View toolbars;
    static class IncludeToolbar {
        @BindView(R.id.textview_toolbar)
        TextView textViewToolbar;

        @BindView(R.id.button_toolbar)
        ImageButton buttonToolbar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setToolbarTitle();
        initInjector();
        initial();
    }

    protected abstract void initInjector();

    private void setToolbarTitle() {
        includeToolbar = new IncludeToolbar();
        ButterKnife.bind(includeToolbar, toolbars);

        includeToolbar.textViewToolbar.setText(getToolbarTitle());
        includeToolbar.buttonToolbar.setImageResource(getButtonIconToolbar());
        includeToolbar.buttonToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickToolbar();
            }
        });
    }

    @Override
    public ApplicationComponent getComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent(new ActivityModule(this));
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) this).getComponent());
    }

    protected abstract void onClickToolbar();

    protected abstract int getButtonIconToolbar();

    protected abstract String getToolbarTitle();

    protected abstract int getLayoutId();

    protected abstract void initial();
}
