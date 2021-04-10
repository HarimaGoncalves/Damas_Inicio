/*     */ package damas;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Button;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Event;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ public class DamasGUI
/*     */   extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JPanel contentPane;
/*     */   Tabuleiro t;
/*     */   Interface_tabuleiro i;
/*     */   int x;
/*     */   int y;
/*     */   Button b1;
/*     */   Button b2;
/*     */   Button b3;
/*     */   Button b4;
/*     */   Button b5;
/*     */   JPanel p;
/*  32 */   int resposta = 0;
/*     */   
/*     */   public static void main(String[] args) {
/*  35 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/*     */             try {
/*  38 */               DamasGUI frame = new DamasGUI();
/*  39 */               frame.setVisible(true);
/*  40 */             } catch (Exception e) {
/*  41 */               e.printStackTrace();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public DamasGUI() {
/*  48 */     setTitle("Jogo das Damas clássicas");
/*  49 */     setDefaultCloseOperation(3);
/*     */ 
/*     */ 
/*     */     
/*  53 */     setSize(600, 600);
/*     */     
/*  55 */     setLocationRelativeTo(null);
/*     */     
/*  57 */     this.contentPane = new JPanel();
/*  58 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  59 */     setContentPane(this.contentPane);
/*  60 */     this.contentPane.setLayout(null);
/*     */     
/*  62 */     if (this.t == null) {
/*  63 */       this.t = new Tabuleiro();
/*     */     }
/*  65 */     getContentPane().setLayout(new BorderLayout());
/*     */     
/*  67 */     if (this.p == null)
/*  68 */       this.p = new JPanel(); 
/*  69 */     this.p.setBorder(BorderFactory.createTitledBorder(
/*  70 */           BorderFactory.createBevelBorder(0), "Painel", 
/*  71 */           1, 0, new Font(
/*  72 */             "Arial", 1, 12), new Color(0, 0, 0)));
/*  73 */     this.p.setLayout(new FlowLayout(0));
/*     */     
/*  75 */     if (this.b1 == null) {
/*  76 */       this.b1 = new Button("Jogar c/ brancas");
/*     */     }
/*  78 */     this.b1.setCursor(new Cursor(12));
/*     */     
/*  80 */     if (this.b2 == null)
/*  81 */       this.b2 = new Button("Jogar c/ pretas"); 
/*  82 */     this.b2.setCursor(new Cursor(12));
/*     */     
/*  84 */     if (this.b3 == null)
/*  85 */       this.b3 = new Button("1"); 
/*  86 */     this.b3.setCursor(new Cursor(12));
/*     */     
/*  88 */     if (this.b4 == null)
/*  89 */       this.b4 = new Button("2"); 
/*  90 */     this.b4.setCursor(new Cursor(12));
/*     */     
/*  92 */     if (this.b5 == null)
/*  93 */       this.b5 = new Button("3"); 
/*  94 */     this.b5.setCursor(new Cursor(12));
/*     */     
/*  96 */     this.p.add(this.b1);
/*  97 */     this.p.add(this.b2);
/*  98 */     this.p.add(this.b3);
/*  99 */     this.p.add(this.b4);
/* 100 */     this.p.add(this.b5);
/*     */     
/* 102 */     getContentPane().add("North", this.p);
/*     */     
/* 104 */     if (this.i == null) {
/* 105 */       this.i = new Interface_tabuleiro(this, this.t, -1);
/*     */     }
/* 107 */     this.i.acabou = 0;
/* 108 */     this.i.computador = 10;
/* 109 */     this.i.level = 5;
/*     */     
/* 111 */     getContentPane().add("Center", this.i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean action(Event evt, Object arg) {
/* 117 */     if ("1".equals(arg)) {
/* 118 */       this.i.level = 5;
/* 119 */       this.i.repaint();
/* 120 */       return true;
/*     */     } 
/*     */     
/* 123 */     if ("2".equals(arg)) {
/* 124 */       this.i.level = 6;
/* 125 */       this.i.repaint();
/* 126 */       return true;
/*     */     } 
/*     */     
/* 129 */     if ("3".equals(arg)) {
/* 130 */       this.i.level = 7;
/* 131 */       this.i.repaint();
/* 132 */       return true;
/*     */     } 
/*     */     
/* 135 */     if ("Jogar c/ brancas".equals(arg)) {
/* 136 */       this.i.recomeca(1);
/*     */     } else {
/*     */       
/* 139 */       this.i.recomeca(-1);
/*     */     } 
/* 141 */     this.i.repaint();
/*     */     
/* 143 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\Downloads\Damas Em Java.jar!\damas\DamasGUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */