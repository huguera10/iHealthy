package com.ihealthy.ihealthy;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDietFragment extends Fragment {

    public MyDietFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_my_diet, container, false);

        setSaveButton(view);

        return view;
    }

    private void setSaveButton(final View view) {
        Button btnSave = (Button) view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_sunday = (EditText) view.findViewById(R.id.et_sunday);
                EditText et_monday = (EditText) view.findViewById(R.id.et_monday);
                EditText et_tuesday = (EditText) view.findViewById(R.id.et_tuesday);
                EditText et_wednesday = (EditText) view.findViewById(R.id.et_wednesday);
                EditText et_thursday = (EditText) view.findViewById(R.id.et_thursday);
                EditText et_friday = (EditText) view.findViewById(R.id.et_friday);
                EditText et_saturday = (EditText) view.findViewById(R.id.et_saturday);

                registerDiet(v, et_sunday,et_monday,et_tuesday,et_wednesday,et_thursday,et_friday,et_saturday);
            }
        });
    }

    private void registerDiet(View view, EditText et_sunday,EditText et_monday,EditText et_tuesday,
                              EditText et_wednesday,EditText et_thursday,EditText et_friday,EditText et_saturday){
        Snackbar.make(view, "Implementar registro de dieta", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
