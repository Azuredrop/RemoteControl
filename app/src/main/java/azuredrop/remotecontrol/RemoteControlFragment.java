package azuredrop.remotecontrol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/1.
 */

public class RemoteControlFragment extends Fragment {
    private TextView mSelectedTextView;
    private TextView mWorkingTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_remote_control, container, false);

        // SelectedTextView
        mSelectedTextView = (TextView) v.findViewById(R.id.fragment_remote_control_selectedTextView);

        // WorkingTextView
        mWorkingTextView = (TextView) v.findViewById(R.id.fragment_remote_control_workingTextView);

        // common number click event
        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = (TextView) v;
                String text = textView.getText().toString();
                String working = mWorkingTextView.getText().toString();
                if (working.equals("0")) {
                    mWorkingTextView.setText(text);
                } else {
                    mWorkingTextView.setText(working + text);
                }
            }
        };

        // number button
        TableLayout tableLayout = (TableLayout) v.findViewById(R.id.fragment_remote_control_tableLayout);
        int number = 1;
        for (int i = 2; i < tableLayout.getChildCount() - 1; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                Button button = (Button) row.getChildAt(j);
                button.setText("" + number);
                button.setOnClickListener(numberButtonListener);
                number++;
            }
        }
        // last row
        TableRow bottomRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() - 1);
        // delete
        Button deleteButton = (Button) bottomRow.getChildAt(0);
        deleteButton.setBackgroundColor(Color.parseColor("#cc0000"));
        deleteButton.setTextAppearance(R.style.RemoteButton_BoldButton);
        deleteButton.setText("Delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkingTextView.setText("0");
            }
        });
        // zero
        Button zeroButton = (Button) bottomRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberButtonListener);
        // enter
        Button enterButton = (Button) bottomRow.getChildAt(2);
        enterButton.setBackgroundColor(Color.parseColor("#66bb00"));
        enterButton.setTextAppearance(R.style.RemoteButton_BoldButton);
        enterButton.setText("Enter");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence working = mWorkingTextView.getText();
                if (working.length() > 0) {
                    mSelectedTextView.setText(working);
                }
                mWorkingTextView.setText("0");
            }
        });

        return v;
    }
}
