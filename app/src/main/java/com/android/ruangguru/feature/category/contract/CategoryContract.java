package com.android.ruangguru.feature.category.contract;

import com.android.ruangguru.feature.category.model.CategoryModel;

import java.util.List;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public interface CategoryContract {

    interface View {

        List<CategoryModel> getListCategory();

    }
}
