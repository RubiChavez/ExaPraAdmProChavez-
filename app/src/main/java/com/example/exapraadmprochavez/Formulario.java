package com.example.exapraadmprochavez;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Formulario extends AppCompatActivity {
    private TextView contrasenia;
    private EditText numEmpleado,dias,nombreE,puestoE,sueldoE;
    private Button btnCalcular;
    private TextView sueldoO;
    //private Integer sueldoBase= 500;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        //contrasenia = (TextView) findViewById(R.id.etContrasenia);
        //integracion XML a JAVA
        numEmpleado = findViewById(R.id.etNumEmpleado);
        nombreE = findViewById(R.id.etNombre);
        puestoE = findViewById(R.id.etPuesto);
        dias = findViewById(R.id.etDias);
        sueldoE = findViewById(R.id.etSueldo);
        sueldoO = findViewById(R.id.txtSueldo);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularSueldoFinal();
            }
        });

    }//termina onCreate
    //metodo para insertar registros tabla empleado
    public void altaEmpleados(View view){
        //Generea un objeto admin que pasa por el com¿nstructor y es donde se trabaja la base de datos, factory queda null porque automaticamente
        //ya se hacen las operaciones de sql
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //queremos que la bd sea rescribible
        SQLiteDatabase bd = admin.getWritableDatabase();//objetos de base de datos se reescribible

        //para guardar valor de variables del formulario
        //toammos las variables del formulario y las guradmos en otras varibles
        String numeroEmpleado = numEmpleado.getText().toString();
        String nombre = nombreE.getText().toString();
        String puesto = puestoE.getText().toString();
        String diasT = dias.getText().toString();
        String sueldo = sueldoE.getText().toString();


        //se crea contenedor para almacenar los valores
        ContentValues registro = new ContentValues();
        //se integran variables de java con valores y campos de la tabla empleado
        registro.put("numEmp", numeroEmpleado);
        registro.put("nombre", nombre);
        registro.put("puesto", puesto);
        registro.put("diasT", diasT);
        registro.put("sueldo", sueldo);
        //se inserta registro en tabla empleado
        //objetos orientdas a aspectos para insertarel valor
        bd.insert("empleado", null, registro);
        // Se cierra BD
        bd.close();
        //Se limpian los campos de texto
        numEmpleado.setText(null);
        nombreE.setText(null);
        puestoE.setText(null);
        dias.setText(null);
        sueldoE.setText(null);

        //Imprimir datos de registro exitoso en ventana emergente tipo TOAST
        Toast.makeText(this, "Exito al ingresar el registro\n\nNumeroEmpelado:"+numEmpleado+"\nNombre:"+nombreE+"\nPuesto:"+puestoE+"Dias T:"+dias+"Sueldo"+sueldoE,Toast.LENGTH_LONG).show();
        //Limpia cajas de texto
        this.numEmpleado.setText("");
        this.nombreE.setText("");
        this.puestoE.setText("");
        this.dias.setText("");
        this.sueldoE.setText("");
    }
    //metodo para consultar
    public void consultaEmpleados(View view){
        //vamos a la ubicacion de la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //reescribimos la base de datos para consultar
        SQLiteDatabase bd = admin.getWritableDatabase();//objetos de base de datos se reescribible

        //se asigna una variable para busqueda y consulta por campo distintivo
        String numEmpleadoConsulta = numEmpleado.getText().toString();
        //Cursor recorre los campos d euna tabla hasta encontralo por campo distintivo
        //cursor busqeda a detalle, se maneja a nivel PLSQL
        //ultimo campo nulo para que no haga otra busqueda
        Cursor fila = bd.rawQuery("SELECT nombre,puesto,diasT,sueldo from empleado where numEmp="+numEmpleadoConsulta,null);

        if(fila.moveToFirst()){//si condicion es verdadera, es decir, encontro un campo y sus datos
            nombreE.setText(fila.getString(0));
            puestoE.setText(fila.getString(1));
            dias.setText(fila.getString(2));
            sueldoE.setText(fila.getString(3));
            Toast.makeText(this,"Registro encontrado de forma EXITOSA",Toast.LENGTH_LONG).show();
        }else{//condicion falsa si no encontro un registro
            Toast.makeText(this,"No existe empleado con ese Codigo\nVerifica",Toast.LENGTH_LONG).show();
            bd.close();
        }
    }
    //metodo borrar
    public void borrarEmpelados(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();//objetos de base de datos  reescribible

        //se asigna variable para busqueda por campo distitivo caodigo producto
        String codigoBaja = numEmpleado.getText().toString();
        //Se genera instrtuccion SQL para que se elimine el registro de producto
        int c = bd.delete("empleado","numEmp="+codigoBaja,null);
        if(c==1){
            Toast.makeText(this,"Registro eliminado de BD exitoso\nVerifica Consulta",Toast.LENGTH_LONG).show();
            //Limpia cajas de texto
            this.numEmpleado.setText("");
            this.nombreE.setText("");
            this.puestoE.setText("");
            this.dias.setText("");
            this.sueldoE.setText("");
        }else{
            Toast.makeText(this,"Error\nNo existe empleado con ese codigo",Toast.LENGTH_LONG).show();
        }
    }
    //metodo editar
    public void editarEmpelados(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();//objetos de base de datos  reescribible

        //se declaran variables que vienen desde formulario sus datos
        String numEmpelado = numEmpleado.getText().toString();
        String nombre = nombreE.getText().toString();
        String puesto = puestoE.getText().toString();
        String diasT = dias.getText().toString();
        String sueldo = sueldoE.getText().toString();

        //se genera un contenedor para almacenar los valores anteriores
        ContentValues registro = new ContentValues();
        registro.put("numEmp",numEmpelado);
        registro.put("nombre",nombre);
        registro.put("puesto",puesto);
        registro.put("diasT",diasT);
        registro.put("sueldo",sueldo);

        //Se crea la variable que contine la instruccion SQL encargada de modificar y almacenar valor 1 si edito
        int cant = bd.update("empleado",registro,"numEmp="+numEmpelado,null);
        bd.close();
        if(cant==1) {//condicion si realizo modificacion
            Toast.makeText(this,"Registro actualizado de forma correcta",Toast.LENGTH_LONG).show();
            //Limpia cajas de texto
            this.numEmpleado.setText("");
            this.nombreE.setText("");
            this.puestoE.setText("");
            this.dias.setText("");
            this.sueldoE.setText("");
        }else {//contrario a no modificacion
            Toast.makeText(this,"Error\nNo se modifico registro",Toast.LENGTH_LONG).show();
        }
    }
    public void calcularSueldoFinal(){

        String diasT = dias.getText().toString();
        String sueldoBono = sueldoE.getText().toString();

        int diasTrabajados = Integer.parseInt(diasT);
        int sueldoBase = Integer.parseInt(sueldoBono);
        int sueldoFinal = sueldoBase;

        if (diasTrabajados>15){
            sueldoFinal = sueldoBase + (int)(sueldoBase * 0.15);
        }
        Toast.makeText(this, "Sueldo final: $" + sueldoFinal, Toast.LENGTH_LONG).show();

        sueldoO.setText("sueldo: $" + sueldoFinal);
    }
}

