package com.business.warthon.login.views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.warthon.R;
import com.business.warthon.utiles.GeneralFragment;

public class MenuPrincipalFragment extends GeneralFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // para aplicar el estilo al fragment.
        getContext().getTheme().applyStyle(R.style.MainThema, true);
        View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);
        this.cargarElementos(view);
        return view;
    }

    @Override
    protected void cargarElementos(View view) {

    }
}
