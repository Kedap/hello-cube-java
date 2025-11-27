package org.example;


import org.jogamp.java3d.utils.behaviors.mouse.MouseRotate;
import org.jogamp.java3d.utils.geometry.ColorCube;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.java3d.BoundingSphere;
import org.jogamp.java3d.BranchGroup;
import org.jogamp.java3d.Canvas3D;
import org.jogamp.java3d.TransformGroup;
import javax.swing.*;
import org.jogamp.vecmath.Point3d;
import java.awt.*;

public class Main extends JPanel {
    SimpleUniverse universo;

    public Main() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        setLayout(new BorderLayout());
        add(canvas3D);

        universo = new SimpleUniverse(canvas3D);
        universo.getViewingPlatform().setNominalViewingTransform();

        BranchGroup escena = crearGrafoEscena();
        escena.compile();

        universo.addBranchGraph(escena);
    }

    public BranchGroup crearGrafoEscena() {
        BranchGroup objetoRaiz = new BranchGroup();

        TransformGroup mouseGroup = new TransformGroup();
        mouseGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        mouseGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objetoRaiz.addChild(mouseGroup);

        ColorCube cubo = new ColorCube(0.4f);
        mouseGroup.addChild(cubo);

        MouseRotate mr = new MouseRotate();
        mr.setTransformGroup(mouseGroup);
        mr.setSchedulingBounds(new BoundingSphere(new Point3d(),1000f));
        objetoRaiz.addChild(mr);

        return objetoRaiz;
    }

    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame ventana = new JFrame("Movimiento con mouse");
        Main panel = new Main();
        ventana.add(panel);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
