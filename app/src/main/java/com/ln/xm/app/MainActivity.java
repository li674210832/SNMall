package com.ln.xm.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ln.xm.R;
import com.ln.xm.base.BaseFragment;
import com.ln.xm.community.fragment.CommunitityFragment;
import com.ln.xm.home.fragment.HomeFragment;
import com.ln.xm.shoppingcart.fragment.ShoppingcardFragment;
import com.ln.xm.type.fragment.TypeFragment;
import com.ln.xm.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;
    @Bind(R.id.rb_group)
    RadioGroup mRbGroup;
    private int position;
    private ArrayList< BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRbGroup.check(R.id.rb_home);

        //初始化Fragment
        initFragment();
        switchFragment(mContent, fragments.get(0));

    }

    /**
     * 根据位置得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = fragments.get(position);
        return fragment;
    }

    public void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunitityFragment());
        fragments.add(new ShoppingcardFragment());
        fragments.add(new UserFragment());
    }

    //选择fragment的对应编号(程序员的世界,万物从0开始)

    @OnClick({R.id.rb_home, R.id.rb_type, R.id.rb_commutity, R.id.rb_cart, R.id.rb_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                position=0;
             //   mRbGroup.check(R.id.rb_home);
                break;
            case R.id.rb_type:
                position=1;
             //   mRbGroup.check(R.id.rb_type);
                break;
            case R.id.rb_commutity:
                position=2;
             //   mRbGroup.check(R.id.rb_commutity);
                break;
            case R.id.rb_cart:
                position=3;
               // mRbGroup.check(R.id.rb_cart);
                break;
            case R.id.rb_user:
                position=4;
               // mRbGroup.check(R.id.rb_user);
                break;
            default:
                position = 0 ;
              //  mRbGroup.check(R.id.rb_home);
                break;
        }

        BaseFragment fragment = getFragment();
        //替换Fragment
        switchFragment(mContent, fragment);
    }

    private void switchFragment(Fragment from, BaseFragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.fl, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

    }

}
