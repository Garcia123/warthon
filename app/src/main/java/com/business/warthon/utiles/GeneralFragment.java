package com.business.warthon.utiles;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.business.warthon.R;

public abstract class GeneralFragment extends Fragment {

    private static final LogWarthon log = LogWarthon.newIntance(GeneralFragment.class.getSimpleName());
    private Activity activity;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private int titulo;
    private int idItem;
    private int resId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.getToolbar().setTitle(titulo);
        this.getToolbar().getMenu().clear();
        this.getNavigationView().setCheckedItem(idItem);
        this.getToolbar().inflateMenu(resId);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * <h1>newBuilder</h1>
     * Crea una instancia de la clase {@code GeneralFragmentBuild} esto permite poder crear instancia de
     * {@code GeneralFragment}
     *
     * @param <T> el tipo de objeto deseado pero tiene que extender de {@code GeneralFragment}
     * @param cls La clase de tipo T
     * @return Retorna una instancia de un hijo de {@code GeneralFragment} del tipo T que envio por parametro.
     */
    public static <T extends GeneralFragment> GeneralFragmentBuild newBulder(Class<T> cls) {
        GeneralFragment gf = null;
        try {
            gf = cls.newInstance();
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (java.lang.InstantiationException e) {
            log.error(e.getMessage(), e);
        }
        return new GeneralFragmentBuildImpl(gf);
    }

    /**
     * <h1>Muestra el progresbar</h1>
     * busca el progresBar del activity padre y el contenedor fragment
     * para ocultar el contenedor y mostrar el progresbar dependiendo del parametro
     *
     * @param estado {@code true} es para mostrar el progresBar y {@code false} para ocultarlo.
     */
    protected void mostrarProgresBar(boolean estado) {

        ProgressBar progressBar = this.getContenActivity().findViewById(R.id.progressBar);
        FrameLayout frameLayout = this.getContenActivity().findViewById(R.id.container);

        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
        frameLayout.setVisibility(estado ? View.GONE : View.VISIBLE);
    }

    protected Activity getContenActivity() {
        return activity;
    }

    protected NavigationView getNavigationView() {
        return navigationView;
    }

    protected Toolbar getToolbar() {
        return toolbar;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment fragment);
    }

    public interface GeneralFragmentBuild {

        GeneralFragmentBuild setContenActivity(Activity activity);

        GeneralFragmentBuild setNavigationView(NavigationView navigationView);

        GeneralFragmentBuild setToolbar(Toolbar toolbar);

        GeneralFragmentBuild setTutulio(int titulo);

        GeneralFragmentBuild setCheckedItem(int idItem);

        GeneralFragmentBuild inflateMenu(int resId);

        <T extends GeneralFragment> T create();
    }

    public static class GeneralFragmentBuildImpl implements GeneralFragmentBuild {

        private GeneralFragment generalFragment;

        public GeneralFragmentBuildImpl(GeneralFragment generalFragment) {
            this.generalFragment = generalFragment;
        }

        @Override
        public GeneralFragmentBuild setContenActivity(Activity activity) {
            generalFragment.activity = activity;
            return this;
        }

        @Override
        public GeneralFragmentBuild setNavigationView(NavigationView navigationView) {
            generalFragment.navigationView = navigationView;
            return this;
        }

        @Override
        public GeneralFragmentBuild setToolbar(Toolbar toolbar) {
            generalFragment.toolbar = toolbar;
            return this;
        }

        @Override
        public GeneralFragmentBuild setTutulio(int titulo) {
            this.generalFragment.titulo = titulo;
            return this;
        }

        @Override
        public GeneralFragmentBuild setCheckedItem(int idItem) {
            this.generalFragment.idItem = idItem;
            return this;
        }

        @Override
        public GeneralFragmentBuild inflateMenu(int resId) {
            this.generalFragment.resId = resId;
            return this;
        }

        @Override
        public <T extends GeneralFragment> T create() {
            return (T)this.generalFragment;
        }
    }

}
