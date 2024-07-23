package com.agenciadevuelosglobales.Menu.PermisosTableFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import user.domain.RolPermisoUsuario.RolPermiso;

public class PermisosTableFrame extends JFrame {
    private JTable table;
    private JButton btnSalir;
    private ArrayList<RolPermiso> permisos;
    private int rolId;
    private int idUsuario;

    public PermisosTableFrame(ArrayList<RolPermiso> permisos, int rolId, int idUsuario) {
        this.permisos = permisos;
        this.rolId = rolId;
        this.idUsuario = idUsuario;

        setTitle("Permisos del Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Numero", "Permiso"};
        String[][] data = new String[permisos.size()][2];
        for (int i = 0; i < permisos.size(); i++) {
            data[i][0] = String.valueOf(i + 1); // Número del permiso (1, 2, 3, ...)
            data[i][1] = permisos.get(i).getNombrePermiso(); // Nombre del permiso
        }

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSalirAction();
            }
        });
        add(btnSalir, BorderLayout.SOUTH);
    }

    private void handleSalirAction() {
        dispose(); // Cierra la ventana actual
        // Puedes eliminar la parte que abre un nuevo menú si no es necesario
        // Si necesitas manejar la navegación de otra forma, implementa aquí el código necesario.
    }
}
