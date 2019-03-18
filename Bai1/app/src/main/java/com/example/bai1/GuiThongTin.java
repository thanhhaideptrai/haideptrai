package com.example.bai1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GuiThongTin extends AppCompatActivity {
    EditText txtHoTen, txtCMND, txtTTBS;
    CheckBox db, ds, dc;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHoTen = findViewById(R.id.txtHoTen);
        txtCMND = findViewById(R.id.txtCMND);
        txtTTBS = findViewById(R.id.txtTTBS);

        db = findViewById(R.id.docbao);
        ds = findViewById(R.id.docsach);
        dc = findViewById(R.id.doccode);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = txtHoTen.getText()+"";
                ten = ten.trim();
                if(ten.length()<3)
                {
                    txtHoTen.requestFocus();
                    txtHoTen.selectAll();
                    Toast.makeText(GuiThongTin.this, "Tên phải >= 3 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                //kiểm tra CMND hợp lệ
                String cmnd=txtCMND.getText()+"";
                cmnd=cmnd.trim();
                if(cmnd.length()!=9)
                {
                    txtCMND.requestFocus();
                    txtCMND.selectAll();
                    Toast.makeText(GuiThongTin.this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
                    return;
                }
                //Kiểm tra bằng cấp
                String bang="";
                RadioGroup group=(RadioGroup) findViewById(R.id.group);
                int id=group.getCheckedRadioButtonId();
                if(id==-1)
                {
                    Toast.makeText(GuiThongTin.this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
                    return;
                }
                RadioButton rad=(RadioButton) findViewById(id);
                bang=rad.getText()+"";
                //Kiểm tra sở thích
                String sothich="";
                if(db.isChecked())
                    sothich+=db.getText()+"\n";
                if(ds.isChecked())
                    sothich+=ds.getText()+"\n";
                if(dc.isChecked())
                    sothich+=dc.getText()+"\n";

                String bosung=txtTTBS.getText()+"";

                AlertDialog.Builder builder=new AlertDialog.Builder(GuiThongTin.this);
                builder.setTitle("Thông tin cá nhân");
                builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                });
                //tạo nội dung
                String msg=ten+"\n";
                msg+= cmnd+"\n";
                msg+=bang+"\n";
                msg+=sothich;
                msg+="-----------------------------\n";
                msg+="Thông tin bổ sung:\n";
                msg+=bosung+ "\n";
                msg+="-----------------------------";
                builder.setMessage(msg);//thiết lập nội dung
                builder.create().show();//hiển thị Dialog
            }
        });
    }


}
