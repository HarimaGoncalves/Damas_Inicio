/*     */ package damas;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Event;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Interface_tabuleiro
/*     */   extends Canvas
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   int level;
/*     */   DamasGUI controller;
/*     */   Tabuleiro tab;
/*     */   Tabuleiro tab_visual;
/*     */   int jogador;
/*     */   int njog;
/*     */   Vector<Par> escolha_jogada;
/*     */   Lista_jogadas admissiveis;
/*     */   int menor;
/*     */   int inter;
/*     */   int cursorx;
/*     */   int cursory;
/*     */   int acabou;
/*     */   public int computador;
/*     */   
/*     */   public void recomeca(int jog) {
/*  49 */     this.njog = 1;
/*  50 */     this.jogador = -1;
/*  51 */     this.computador = jog;
/*  52 */     this.acabou = 0;
/*  53 */     this.tab = new Tabuleiro();
/*  54 */     this.tab_visual = new Tabuleiro();
/*  55 */     this.tab.copia(this.tab_visual);
/*  56 */     this.cursorx = -1;
/*  57 */     this.cursory = -1;
/*  58 */     this.inter = 1;
/*  59 */     this.admissiveis = new Lista_jogadas();
/*  60 */     this.tab.Lista_admissiveis(this.tab, this.jogador, this.admissiveis);
/*  61 */     this.escolha_jogada = new Vector<Par>();
/*     */     
/*  63 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Interface_tabuleiro(DamasGUI controller, Tabuleiro tab, int jogador) {
/*  76 */     this.cursorx = -1;
/*  77 */     this.cursory = -1;
/*  78 */     this.njog = 1;
/*  79 */     this.inter = 1;
/*  80 */     this.controller = controller;
/*  81 */     this.tab = tab;
/*  82 */     this.jogador = jogador;
/*  83 */     this.tab_visual = new Tabuleiro();
/*  84 */     tab.copia(this.tab_visual);
/*  85 */     this.admissiveis = new Lista_jogadas();
/*  86 */     tab.Lista_admissiveis(tab, jogador, this.admissiveis);
/*  87 */     this.escolha_jogada = new Vector<Par>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseDown(Event evt, int x, int y) {
/*  94 */     if (this.computador == 10) {
/*  95 */       return false;
/*     */     }
/*  97 */     if (this.jogador == this.computador) {
/*  98 */       return false;
/*     */     }
/* 100 */     if (this.acabou > 0) {
/* 101 */       return false;
/*     */     }
/* 103 */     if (this.admissiveis.movimentos.size() == 0) {
/*     */       
/* 105 */       this.acabou = this.computador;
/*     */       
/* 107 */       repaint();
/*     */       
/* 109 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 113 */     int m = this.menor / 9;
/*     */     
/* 115 */     int wx = x / m;
/* 116 */     int wy = y / m;
/*     */     
/* 118 */     if ((wx + wy) % 2 == 1) {
/* 119 */       return true;
/*     */     }
/* 121 */     if (wx > 7 || wy > 7 || wx < 0 || wy < 0) {
/* 122 */       return true;
/*     */     }
/* 124 */     if (this.escolha_jogada.size() == 0 && this.tab.matriz[wy][wx] * this.jogador <= 0) {
/* 125 */       return false;
/*     */     }
/* 127 */     this.escolha_jogada.addElement(new Par(wy, wx));
/*     */ 
/*     */     
/* 130 */     this.tab.copia(this.tab_visual);
/*     */     
/* 132 */     int resul = verifica_jogada(this.escolha_jogada, this.tab_visual);
/*     */     
/* 134 */     if (resul == 0) {
/* 135 */       this.cursorx = -1;
/* 136 */       this.cursory = -1;
/* 137 */       this.escolha_jogada.removeAllElements();
/* 138 */       repaint();
/* 139 */       return false;
/*     */     } 
/*     */     
/* 142 */     this.cursorx = wx;
/*     */     
/* 144 */     this.cursory = wy;
/*     */     
/* 146 */     if (resul == 1) {
/* 147 */       repaint();
/* 148 */       return false;
/*     */     } 
/*     */     
/* 151 */     this.cursorx = -1;
/*     */     
/* 153 */     repaint();
/*     */     
/* 155 */     this.tab_visual.copia(this.tab);
/*     */     
/* 157 */     this.escolha_jogada.removeAllElements();
/*     */     
/* 159 */     this.jogador = -this.jogador;
/*     */     
/* 161 */     this.njog++;
/*     */     
/* 163 */     repaint();
/*     */     
/* 165 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int verifica_jogada(Vector<Par> escolha, Tabuleiro tabuleiro1) {
/* 175 */     if (escolha.size() == 1) {
/*     */ 
/*     */       
/* 178 */       for (int j = 0; j < this.admissiveis.movimentos.size(); j++) {
/*     */         
/* 180 */         Vector<?> v1 = (Vector<?>) this.admissiveis.movimentos.elementAt(j);
/*     */         
/* 182 */         int ex = ((Par)escolha.elementAt(0)).x;
/*     */         
/* 184 */         int ey = ((Par)escolha.elementAt(0)).y;
/*     */         
/* 186 */         int vx = ((Par)v1.elementAt(0)).x;
/*     */         
/* 188 */         int vy = ((Par)v1.elementAt(0)).y;
/*     */         
/* 190 */         if (ex == vx && ey == vy) {
/* 191 */           return 1;
/*     */         }
/*     */       } 
/*     */       
/* 195 */       return 0;
/*     */     } 
/*     */     
/* 198 */     for (int i = 0; i < this.admissiveis.movimentos.size(); i++) {
/*     */       
/* 200 */       Vector<?> v1 = (Vector<?>) this.admissiveis.movimentos.elementAt(i);
/*     */       
/* 202 */       if (v1.size() >= escolha.size()) {
/*     */         int j;
/*     */         
/* 205 */         for (j = 0; j < escolha.size(); j++) {
/*     */           
/* 207 */           int ex = ((Par)escolha.elementAt(j)).x;
/*     */           
/* 209 */           int ey = ((Par)escolha.elementAt(j)).y;
/*     */           
/* 211 */           int vx = ((Par)v1.elementAt(j)).x;
/*     */           
/* 213 */           int vy = ((Par)v1.elementAt(j)).y;
/*     */           
/* 215 */           if (ex != vx || ey != vy) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */         
/* 220 */         if (j == escolha.size()) {
/*     */           
/* 222 */           aplica(escolha, tabuleiro1);
/*     */           
/* 224 */           if (v1.size() == escolha.size()) {
/* 225 */             return 2;
/*     */           }
/* 227 */           return 1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 233 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void aplica(Vector<Par> movimentos, Tabuleiro tabuleiro1) {
/* 243 */     if (movimentos.size() == 0) {
/*     */       return;
/*     */     }
/* 246 */     int x1 = ((Par)movimentos.elementAt(0)).x;
/*     */     
/* 248 */     int y1 = ((Par)movimentos.elementAt(0)).y;
/*     */     
/* 250 */     int guarda = tabuleiro1.matriz[x1][y1];
/*     */     
/* 252 */     for (int i = 0; i < movimentos.size() - 1; i++) {
/*     */       int ix, iy;
/* 254 */       x1 = ((Par)movimentos.elementAt(i)).x;
/*     */       
/* 256 */       y1 = ((Par)movimentos.elementAt(i)).y;
/*     */       
/* 258 */       int j = ((Par)movimentos.elementAt(i + 1)).x;
/*     */       
/* 260 */       int k = ((Par)movimentos.elementAt(i + 1)).y;
/*     */       
/* 262 */       if (j > x1) {
/* 263 */         ix = 1;
/*     */       } else {
/* 265 */         ix = -1;
/*     */       } 
/* 267 */       if (k > y1) {
/* 268 */         iy = 1;
/*     */       } else {
/* 270 */         iy = -1;
/*     */       } 
/* 272 */       for (int cx = x1, cy = y1; cx != j; cx += ix, cy += iy)
/*     */       {
/* 274 */         tabuleiro1.matriz[cx][cy] = 0;
/*     */       }
/* 276 */       tabuleiro1.matriz[j][k] = guarda;
/*     */     } 
/*     */ 
/*     */     
/* 280 */     int x2 = ((Par)movimentos.elementAt(movimentos.size() - 1)).x;
/*     */     
/* 282 */     int y2 = ((Par)movimentos.elementAt(movimentos.size() - 1)).y;
/*     */ 
/*     */     
/* 285 */     if (Math.abs(tabuleiro1.matriz[x2][y2]) == 2) {
/*     */       return;
/*     */     }
/* 288 */     if (guarda > 0 && x2 == 7) {
/* 289 */       tabuleiro1.matriz[x2][y2] = tabuleiro1.matriz[x2][y2] * 2;
/*     */     }
/* 291 */     if (guarda < 0 && x2 == 0) {
/* 292 */       tabuleiro1.matriz[x2][y2] = tabuleiro1.matriz[x2][y2] * 2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 299 */     Graphics2D g2d = (Graphics2D)g.create();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 305 */     if (this.computador == 10) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 310 */     if ((getSize()).width < (getSize()).height) {
/*     */ 
/*     */       
/* 313 */       this.menor = (getSize()).width;
/*     */     }
/*     */     else {
/*     */       
/* 317 */       this.menor = (getSize()).height;
/*     */     } 
/* 319 */     int m = this.menor / 9;
/*     */     
/* 321 */     if (this.acabou != 0) {
/*     */       
/* 323 */       g2d.setColor(Color.white);
/*     */ 
/*     */ 
/*     */       
/* 327 */       g2d.fillRect(0, 0, (getSize()).width, (getSize()).height);
/*     */       
/* 329 */       g2d.setColor(Color.black);
/*     */       
/* 331 */       if (this.acabou == 1)
/*     */       {
/* 333 */         if (this.computador == 1) {
/* 334 */           g2d.setFont(new Font("Arial", 1, 48));
/* 335 */           g2d.drawString("Ganhei o Jogo!!!! :-)", 4 * m, 4 * m);
/*     */         } else {
/*     */           
/* 338 */           g2d.drawString("Perdi o Jogo!!!! :-(", 4 * m, 4 * m);
/*     */         }  } 
/* 340 */       if (this.acabou == -1)
/*     */       {
/* 342 */         if (this.computador == -1) {
/* 343 */           g2d.setFont(new Font("Arial", 1, 48));
/* 344 */           g2d.drawString("Ganhei o Jogo!!!! :-)", 4 * m, 4 * m);
/*     */         } else {
/*     */           
/* 347 */           g2d.drawString("Perdi o Jogo!!!! :-(", 4 * m, 4 * m);
/*     */         }  } 
/* 349 */       this.computador = 10;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 355 */     for (int i = 0; i < 8; i++) {
/*     */       
/* 357 */       for (int j = 0; j < 8; j++) {
/* 358 */         if ((i + j) % 2 == 0) {
/*     */ 
/*     */ 
/*     */           
/* 362 */           g2d.setStroke(new BasicStroke(1.0F));
/*     */ 
/*     */           
/* 365 */           g2d.setColor(Color.gray);
/* 366 */           g2d.fillRect(i * m, j * m, m + 1, m + 1);
/*     */         } else {
/* 368 */           g2d.setColor(Color.gray);
/* 369 */           g2d.drawRect(i * m, j * m, m, m);
/*     */         } 
/*     */         
/* 372 */         if (this.tab_visual.matriz[j][i] == 1) {
/* 373 */           drawPeca(i, j, 1, g2d);
/*     */         }
/* 375 */         if (this.tab_visual.matriz[j][i] == -1) {
/* 376 */           drawPeca(i, j, 0, g2d);
/*     */         }
/* 378 */         if (this.tab_visual.matriz[j][i] == 2) {
/* 379 */           drawDama(i, j, 1, g2d);
/*     */         }
/* 381 */         if (this.tab_visual.matriz[j][i] == -2) {
/* 382 */           drawDama(i, j, 0, g2d);
/*     */         }
/*     */       } 
/*     */     } 
/* 386 */     drawPeca(2, 8, (this.jogador > 0) ? 1 : 0, g2d);
/*     */     
/* 388 */     g2d.setColor(Color.blue);
/* 389 */     g2d.drawString("Jogada: " + this.njog + " Nível= " + (this.level - 4), 5 * m, 
/* 390 */         9 * m);
/*     */     
/* 392 */     g2d.setColor(Color.black);
/*     */ 
/*     */     
/* 395 */     if (this.cursorx >= 0 && this.cursory >= 0) {
/*     */ 
/*     */ 
/*     */       
/* 399 */       setCursor(new Cursor(12));
/* 400 */       g2d.setColor(Color.red);
/* 401 */       int a = this.cursorx * m;
/* 402 */       int b = this.cursory * m;
/* 403 */       g2d.drawLine(a + m / 3, b + m / 3, a + 2 * m / 3, b + 2 * m / 3);
/* 404 */       g2d.drawLine(a + 2 * m / 3, b + m / 3, a + m / 3, b + 2 * m / 3);
/* 405 */       g2d.setColor(Color.black);
/*     */     } 
/*     */ 
/*     */     
/* 409 */     if (this.jogador == this.computador) {
/*     */       
/* 411 */       this.tab.Lista_admissiveis(this.tab, this.jogador, this.admissiveis);
/*     */       
/* 413 */       if (this.admissiveis.movimentos.size() == 0) {
/*     */         
/* 415 */         this.acabou = -this.computador;
/* 416 */         repaint();
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 422 */       this.tab.jogar_bem(this.tab, this.level, this.tab_visual, this.jogador);
/* 423 */       this.tab_visual.copia(this.tab);
/* 424 */       this.njog++;
/* 425 */       this.jogador = -this.jogador;
/* 426 */       this.tab.Lista_admissiveis(this.tab, this.jogador, this.admissiveis);
/*     */       
/* 428 */       repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawPeca(int x, int y, int cor, Graphics g2d) {
/* 438 */     int m = this.menor / 9;
/* 439 */     int pe = 8 * m / 10;
/* 440 */     int pe1x = pe;
/* 441 */     int pe1y = 60 * pe / 100;
/*     */     
/* 443 */     if (cor == 0) {
/* 444 */       g2d.setColor(Color.white);
/*     */     } else {
/* 446 */       g2d.setColor(Color.black);
/*     */     } 
/* 448 */     g2d.fillOval(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2, pe1x, 
/* 449 */         pe1y);
/*     */     
/* 451 */     if (cor == 0) {
/* 452 */       g2d.setColor(Color.black);
/*     */     } else {
/* 454 */       g2d.setColor(Color.white);
/*     */     } 
/*     */     
/* 457 */     g2d.drawArc(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2, pe1x, 
/* 458 */         pe1y, 0, -180);
/*     */     
/* 460 */     g2d.drawArc(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2 + 1, 
/* 461 */         pe1x, pe1y, 0, -180);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawDama(int x, int y, int cor, Graphics g2d) {
/* 469 */     int m = this.menor / 9;
/* 470 */     int pe = 8 * m / 10;
/* 471 */     int pe1x = pe;
/* 472 */     int pe1y = 60 * pe / 100;
/* 473 */     pe /= 5;
/*     */     
/* 475 */     if (cor == 0) {
/* 476 */       g2d.setColor(Color.white);
/*     */     } else {
/* 478 */       g2d.setColor(Color.black);
/*     */     } 
/* 480 */     g2d.fillOval(x * m + m / 2 - pe1x / 2, pe + y * m + m / 2 - pe1y / 2, 
/* 481 */         pe1x, pe1y);
/*     */     
/* 483 */     if (cor == 0) {
/* 484 */       g2d.setColor(Color.black);
/*     */     } else {
/* 486 */       g2d.setColor(Color.white);
/*     */     } 
/*     */     
/* 489 */     g2d.drawArc(x * m + m / 2 - pe1x / 2, pe + y * m + m / 2 - pe1y / 2, 
/* 490 */         pe1x, pe1y, 0, -180);
/*     */     
/* 492 */     g2d.drawArc(x * m + m / 2 - pe1x / 2, 
/* 493 */         pe + y * m + m / 2 - pe1y / 2 + 1, pe1x, pe1y, 0, -180);
/*     */     
/* 495 */     if (cor == 0) {
/* 496 */       g2d.setColor(Color.white);
/*     */     } else {
/* 498 */       g2d.setColor(Color.black);
/*     */     } 
/* 500 */     g2d.fillOval(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2, pe1x, 
/* 501 */         pe1y);
/*     */     
/* 503 */     if (cor == 0) {
/* 504 */       g2d.setColor(Color.black);
/*     */     } else {
/* 506 */       g2d.setColor(Color.white);
/*     */     } 
/*     */     
/* 509 */     g2d.drawArc(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2, pe1x, 
/* 510 */         pe1y, 0, -180);
/*     */     
/* 512 */     g2d.drawArc(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2 + 1, 
/* 513 */         pe1x, pe1y, 0, -180);
/*     */   }
/*     */ }


/* Location:              D:\Downloads\Damas Em Java.jar!\damas\Interface_tabuleiro.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */