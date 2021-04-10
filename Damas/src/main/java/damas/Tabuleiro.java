/*     */ package damas;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Tabuleiro
/*     */ {
/*  26 */   Lista_jogadas admissiveis = new Lista_jogadas();
/*  27 */   public int[][] matriz = new int[8][]; public Tabuleiro() {
/*     */     int i;
/*  29 */     for (i = 0; i < 8; i++)
/*     */     {
/*  31 */       this.matriz[i] = new int[8];
/*     */     }
/*  33 */     for (i = 0; i < 8; i++) {
/*     */       
/*  35 */       for (int j = 0; j < 8; j++)
/*  36 */         this.matriz[i][j] = 0; 
/*     */     } 
/*  38 */     for (i = 0; i < 3; i++) {
/*     */       
/*  40 */       for (int j = 0; j < 8; j++) {
/*     */         
/*  42 */         if ((i + j) % 2 == 0) {
/*  43 */           this.matriz[i][j] = 1;
/*     */         }
/*  45 */         if ((7 - i + j) % 2 == 0) {
/*  46 */           this.matriz[7 - i][j] = -1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean Lista_admissiveis(Tabuleiro t1, int jogador, Lista_jogadas lista_jogadas) {
/*  59 */     Score best_score = new Score(0);
/*  60 */     Vector<Par> movimentos = new Vector<Par>();
/*  61 */     lista_jogadas.movimentos.removeAllElements();
/*  62 */     lista_jogadas.instavel.removeAllElements();
/*     */     
/*  64 */     for (int i = 0; i < 8; i++) {
/*  65 */       for (int j = 0; j < 8; j++) {
/*     */         
/*  67 */         if (t1.matriz[i][j] * jogador > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  73 */           int dama = Math.abs(t1.matriz[i][j]);
/*     */           
/*  75 */           if (dama == 1) {
/*     */             
/*  77 */             movimentos.removeAllElements();
/*     */             
/*  79 */             explora_jogada_peca(jogador, i, j, t1, movimentos, 
/*  80 */                 lista_jogadas, best_score, 0);
/*     */           } 
/*     */ 
/*     */           
/*  84 */           if (dama == 2) {
/*     */             
/*  86 */             movimentos.removeAllElements();
/*     */             
/*  88 */             explora_jogada_dama(i, j, t1, movimentos, lista_jogadas, 
/*  89 */                 best_score, 0);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void explora_jogada_peca_movendo(int jogador, int x, int y, Tabuleiro t1, Vector<Par> movimentos, Lista_jogadas lista_jogadas, Score best_score, int score) {
/* 105 */     int fl = 0;
/*     */     
/* 107 */     if (p_move(x, y, 1, 1, t1)) {
/*     */       
/* 109 */       movimentos.removeAllElements();
/* 110 */       movimentos.addElement(new Par(x, y));
/* 111 */       movimentos.addElement(new Par(x + jogador, y + 1));
/* 112 */       Vector<Par> mov = new Vector<Par>();
/* 113 */       mov = (Vector<Par>)movimentos.clone();
/* 114 */       lista_jogadas.movimentos.addElement(mov);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       if (jogador == 1 && x + jogador == 6) {
/*     */         
/* 121 */         if (y + 1 < 8 && t1.matriz[7][y + 1] == 0) {
/* 122 */           fl = 1;
/*     */         }
/* 124 */         if (y + 1 >= 0 && t1.matriz[7][y - 1] == 0) {
/* 125 */           fl = 1;
/*     */         }
/* 127 */       } else if (jogador == 1 && x + jogador == 0) {
/*     */         
/* 129 */         if (y + 1 < 8 && t1.matriz[0][y + 1] == 0) {
/* 130 */           fl = 1;
/*     */         }
/* 132 */         if (y + 1 >= 0 && t1.matriz[0][y - 1] == 0) {
/* 133 */           fl = 1;
/*     */         }
/*     */       } 
/*     */       
/* 137 */       if (fl == 1) {
/*     */         
/* 139 */         lista_jogadas.instavel.addElement(new Boolean(true));
/*     */       }
/*     */       else {
/*     */         
/* 143 */         lista_jogadas.instavel.addElement(new Boolean(false));
/*     */       } 
/* 145 */       movimentos.removeAllElements();
/*     */     } 
/*     */ 
/*     */     
/* 149 */     fl = 0;
/*     */     
/* 151 */     if (p_move(x, y, 1, -1, t1)) {
/*     */       
/* 153 */       movimentos.removeAllElements();
/* 154 */       movimentos.addElement(new Par(x, y));
/* 155 */       movimentos.addElement(new Par(x + jogador, y - 1));
/* 156 */       Vector<Par> mov = new Vector<Par>();
/* 157 */       mov = (Vector<Par>)movimentos.clone();
/* 158 */       lista_jogadas.movimentos.addElement(mov);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 163 */       if (jogador == 1 && x + jogador == 6) {
/*     */         
/* 165 */         if (y + 1 < 8 && t1.matriz[7][y + 1] == 0) {
/* 166 */           fl = 1;
/*     */         }
/* 168 */         if (y + 1 >= 0 && t1.matriz[7][y - 1] == 0) {
/* 169 */           fl = 1;
/*     */         }
/* 171 */       } else if (jogador == 1 && x + jogador == 0) {
/*     */         
/* 173 */         if (y + 1 < 8 && t1.matriz[0][y + 1] == 0) {
/* 174 */           fl = 1;
/*     */         }
/* 176 */         if (y + 1 >= 0 && t1.matriz[0][y - 1] == 0) {
/* 177 */           fl = 1;
/*     */         }
/*     */       } 
/*     */       
/* 181 */       if (fl == 1) {
/*     */         
/* 183 */         lista_jogadas.instavel.addElement(new Boolean(true));
/*     */       }
/*     */       else {
/*     */         
/* 187 */         lista_jogadas.instavel.addElement(new Boolean(false));
/*     */       } 
/* 189 */       movimentos.removeAllElements();
/*     */     } 
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
/*     */   public void explora_jogada_dama_movendo(int x, int y, Tabuleiro tabuleiro1, Vector<Par> movimentos, Lista_jogadas lista_jogadas, Score best_score, int score) {
/* 203 */     for (int dirx = -1; dirx <= 1; dirx += 2) {
/*     */       
/* 205 */       for (int diry = -1; diry <= 1; diry += 2) {
/*     */         
/* 207 */         for (int ncasas = 1;; ncasas++)
/*     */         
/* 209 */         { int wx = x + ncasas * dirx;
/*     */           
/* 211 */           int wy = y + ncasas * diry;
/*     */           
/* 213 */           if (wx < 0 || wx > 7 || wy < 0 || wy > 7) {
/*     */             break;
/*     */           }
/* 216 */           if (tabuleiro1.matriz[wx][wy] != 0) {
/*     */             break;
/*     */           }
/* 219 */           movimentos.removeAllElements();
/* 220 */           movimentos.addElement(new Par(x, y));
/* 221 */           movimentos.addElement(new Par(wx, wy));
/* 222 */           Vector<Par> mov = new Vector<Par>();
/* 223 */           mov = (Vector<Par>)movimentos.clone();
/* 224 */           lista_jogadas.movimentos.addElement(mov);
/*     */ 
/*     */           
/* 227 */           lista_jogadas.instavel.addElement(new Boolean(false)); } 
/*     */       } 
/* 229 */     }  movimentos.removeAllElements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void explora_jogada_peca(int jogador, int x, int y, Tabuleiro t1, Vector<Par> movimentos, Lista_jogadas lista_jogadas, Score best_score, int score) {
/* 236 */     explora_jogada_peca_comendo(jogador, x, y, t1, movimentos, 
/* 237 */         lista_jogadas, best_score, score);
/*     */     
/* 239 */     if (best_score.valor == 0) {
/* 240 */       explora_jogada_peca_movendo(jogador, x, y, t1, movimentos, 
/* 241 */           lista_jogadas, best_score, score);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void explora_jogada_dama(int x, int y, Tabuleiro t1, Vector<Par> movimentos, Lista_jogadas lista_jogadas, Score best_score, int score) {
/* 251 */     explora_jogada_dama_comendo(0, x, y, t1, movimentos, lista_jogadas, 
/* 252 */         best_score, 0);
/*     */     
/* 254 */     if (best_score.valor == 0) {
/* 255 */       explora_jogada_dama_movendo(x, y, t1, movimentos, lista_jogadas, 
/* 256 */           best_score, score);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void explora_jogada_peca_comendo(int jogador, int x, int y, Tabuleiro t1, Vector<Par> movimentos, Lista_jogadas lista_jogadas, Score best_score, int score) {
/* 268 */     Tabuleiro t2 = new Tabuleiro();
/* 269 */     t1.copia(t2);
/* 270 */     int score_ant = score;
/* 271 */     boolean moveu = false;
/*     */     
/* 273 */     if (p_come(x, y, 1, 1, t1, t2)) {
/*     */ 
/*     */       
/* 276 */       int dama = Math.abs(t1.matriz[x + jogador][y + 1]);
/*     */       
/* 278 */       if (dama == 1) {
/* 279 */         score += 50;
/*     */       } else {
/*     */         
/* 282 */         score += 51;
/*     */       } 
/* 284 */       movimentos.addElement(new Par(x, y));
/* 285 */       explora_jogada_peca_comendo(jogador, x + 2 * jogador, y + 2, t2, 
/* 286 */           movimentos, lista_jogadas, best_score, score);
/* 287 */       int i = movimentos.size() - 1;
/* 288 */       movimentos.removeElementAt(i);
/* 289 */       score = score_ant;
/* 290 */       moveu = true;
/*     */     } 
/*     */     
/* 293 */     t1.copia(t2);
/*     */     
/* 295 */     if (p_come(x, y, 1, -1, t1, t2)) {
/*     */       
/* 297 */       int dama = Math.abs(t1.matriz[x + jogador][y - 1]);
/*     */       
/* 299 */       if (dama == 1) {
/* 300 */         score += 50;
/*     */       } else {
/*     */         
/* 303 */         score += 51;
/*     */       } 
/* 305 */       movimentos.addElement(new Par(x, y));
/* 306 */       explora_jogada_peca_comendo(jogador, x + 2 * jogador, y - 2, t2, 
/* 307 */           movimentos, lista_jogadas, best_score, score);
/* 308 */       int i = movimentos.size() - 1;
/* 309 */       movimentos.removeElementAt(i);
/* 310 */       score = score_ant;
/* 311 */       moveu = true;
/*     */     } 
/*     */     
/* 314 */     if (moveu) {
/*     */       return;
/*     */     }
/* 317 */     if (score == 0) {
/*     */       return;
/*     */     }
/* 320 */     if (score < best_score.valor) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 327 */     movimentos.addElement(new Par(x, y));
/*     */     
/* 329 */     if (score > best_score.valor) {
/*     */       
/* 331 */       best_score.valor = score;
/* 332 */       lista_jogadas.movimentos.removeAllElements();
/* 333 */       lista_jogadas.instavel.removeAllElements();
/*     */     } 
/*     */ 
/*     */     
/* 337 */     Vector<Par> mov = new Vector<Par>();
/* 338 */     mov = (Vector<Par>)movimentos.clone();
/* 339 */     lista_jogadas.movimentos.addElement(mov);
/*     */ 
/*     */     
/* 342 */     lista_jogadas.instavel.addElement(new Boolean(true));
/* 343 */     int nelem = movimentos.size() - 1;
/* 344 */     movimentos.removeElementAt(nelem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void explora_jogada_dama_comendo(int proibido, int x, int y, Tabuleiro t1, Vector<Par> movimentos, Lista_jogadas lista_jogadas, Score best_score, int score) {
/* 355 */     Score val = new Score(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 361 */     Tabuleiro t2 = new Tabuleiro();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 366 */     boolean moveu = false;
/*     */     
/* 368 */     for (int dirx = -1; dirx <= 1; dirx += 2) {
/*     */       
/* 370 */       for (int diry = -1; diry <= 1; diry += 2) {
/*     */         
/* 372 */         if (proibido != 1 || dirx * diry >= 0)
/*     */         {
/*     */           
/* 375 */           if (proibido != 2 || dirx * diry <= 0)
/*     */           {
/*     */             
/* 378 */             if (x + 2 * dirx >= 0 && x + 2 * dirx <= 7 && y + 2 * diry >= 0 && 
/* 379 */               y + 2 * diry <= 7)
/*     */             {
/*     */               
/* 382 */               if (t1.matriz[x + dirx][y + diry] * t1.matriz[x][y] <= 0) {
/*     */ 
/*     */                 
/* 385 */                 int score_parcial = 0;
/* 386 */                 boolean inimigos = false;
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
/* 398 */                 t1.copia(t2);
/* 399 */                 t2.matriz[x][y] = 0;
/* 400 */                 t2.matriz[x + dirx][y + diry] = 0;
/*     */                 
/* 402 */                 int ncasas = 1;
/*     */                 
/* 404 */                 for (; x + ncasas * dirx >= 0 && x + ncasas * dirx <= 7 && 
/* 405 */                   y + ncasas * diry >= 0 && y + ncasas * diry <= 7; ncasas++) {
/*     */ 
/*     */                   
/* 408 */                   t2.matriz[x + ncasas * dirx][y + ncasas * diry] = 0;
/*     */                   
/* 410 */                   if (t1.matriz[x + ncasas * dirx][y + ncasas * diry] * t1.matriz[x][y] > 0) {
/*     */                     break;
/*     */                   }
/* 413 */                   if (t1.matriz[x + ncasas * dirx][y + ncasas * diry] * 
/* 414 */                     t1.matriz[x][y] < 0)
/*     */                   
/* 416 */                   { if (inimigos) {
/*     */                       break;
/*     */                     }
/* 419 */                     inimigos = true;
/*     */                     
/* 421 */                     if (Math.abs(t1.matriz[x + ncasas * dirx][y + ncasas * 
/* 422 */                           diry]) == 2) {
/* 423 */                       score_parcial += 51;
/*     */                     } else {
/* 425 */                       score_parcial += 50;
/*     */                     
/*     */                     }
/*     */                     
/*     */                      }
/*     */                   
/* 431 */                   else if (score_parcial != 0)
/*     */                   { int proi;
/*     */                     
/* 434 */                     inimigos = false;
/* 435 */                     t2.matriz[x + ncasas * dirx][y + ncasas * diry] = t1.matriz[x][y];
/* 436 */                     movimentos.addElement(new Par(x, y));
/*     */                     
/* 438 */                     if (dirx * diry > 0) {
/* 439 */                       proi = 2;
/*     */                     } else {
/* 441 */                       proi = 1;
/*     */                     } 
/* 443 */                     explora_jogada_dama_comendo(proi, x + dirx * ncasas, y + 
/* 444 */                         diry * ncasas, t2, movimentos, lista_jogadas, 
/* 445 */                         best_score, score + score_parcial);
/*     */                     
/* 447 */                     int i = movimentos.size() - 1;
/* 448 */                     movimentos.removeElementAt(i);
/* 449 */                     moveu = true; } 
/*     */                 } 
/*     */               }  }  }  } 
/*     */       } 
/* 453 */     }  if (moveu) {
/*     */       return;
/*     */     }
/* 456 */     if (score == 0) {
/*     */       return;
/*     */     }
/* 459 */     if (score < best_score.valor) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 466 */     movimentos.addElement(new Par(x, y));
/*     */     
/* 468 */     if (score > best_score.valor) {
/*     */       
/* 470 */       best_score.valor = score;
/* 471 */       lista_jogadas.movimentos.removeAllElements();
/* 472 */       lista_jogadas.instavel.removeAllElements();
/*     */     } 
/*     */     
/* 475 */     Vector<Par> mov = new Vector<Par>();
/* 476 */     mov = (Vector<Par>)movimentos.clone();
/* 477 */     lista_jogadas.movimentos.addElement(mov);
/*     */ 
/*     */     
/* 480 */     lista_jogadas.instavel.addElement(new Boolean(true));
/* 481 */     int nelem = movimentos.size() - 1;
/* 482 */     movimentos.removeElementAt(nelem);
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
/*     */   public void copia(Tabuleiro t2) {
/* 494 */     for (int i = 0; i < 8; i++) {
/*     */       
/* 496 */       for (int j = 0; j < 8; j++)
/*     */       {
/* 498 */         t2.matriz[i][j] = this.matriz[i][j];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean p_come(int x, int y, int dirx, int diry, Tabuleiro tabuleiro1, Tabuleiro tabuleiro2) {
/* 508 */     int positivo = tabuleiro1.matriz[x][y];
/*     */     
/* 510 */     if (positivo < 0) {
/* 511 */       dirx = -dirx;
/*     */     }
/*     */     
/* 514 */     int wx = x + dirx + dirx;
/* 515 */     int wy = y + diry + diry;
/*     */     
/* 517 */     if (wx > 7 || wx < 0 || wy > 7 || wy < 0) {
/* 518 */       return false;
/*     */     }
/* 520 */     if (tabuleiro1.matriz[wx][wy] != 0) {
/* 521 */       return false;
/*     */     }
/* 523 */     if (positivo * tabuleiro1.matriz[x + dirx][y + diry] >= 0) {
/* 524 */       return false;
/*     */     }
/* 526 */     tabuleiro2.matriz[wx][wy] = tabuleiro2.matriz[x][y];
/* 527 */     tabuleiro2.matriz[x + dirx][y + diry] = 0;
/* 528 */     tabuleiro2.matriz[x][y] = 0;
/*     */     
/* 530 */     if (positivo > 0 && wy == 7) {
/* 531 */       tabuleiro2.matriz[wx][wy] = tabuleiro2.matriz[wx][wy] * 2;
/*     */     }
/* 533 */     if (positivo < 0 && wy == 0) {
/* 534 */       tabuleiro2.matriz[wx][wy] = tabuleiro2.matriz[wx][wy] * 2;
/*     */     }
/* 536 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean p_move(int x, int y, int dirx, int diry, Tabuleiro tabuleiro1) {
/* 547 */     int positivo = tabuleiro1.matriz[x][y];
/*     */     
/* 549 */     if (positivo < 0) {
/* 550 */       dirx = -dirx;
/*     */     }
/* 552 */     int wx = x + dirx;
/* 553 */     int wy = y + diry;
/*     */     
/* 555 */     if (wx > 7 || wx < 0 || wy > 7 || wy < 0) {
/* 556 */       return false;
/*     */     }
/* 558 */     if (tabuleiro1.matriz[wx][wy] != 0) {
/* 559 */       return false;
/*     */     }
/* 561 */     return true;
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
/*     */   boolean d_come(int x, int y, int dirx, int diry, int ncasas, Tabuleiro tabuleiro1, Tabuleiro tabuleiro2, Score score) {
/* 585 */     int wx = x + ncasas * dirx;
/*     */     
/* 587 */     int wy = y + ncasas * diry;
/*     */     
/* 589 */     if (wx > 7 || wx < 0 || wy > 7 || wy < 0) {
/* 590 */       return false;
/*     */     }
/* 592 */     if (tabuleiro1.matriz[wx][wy] != 0) {
/* 593 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 599 */     int positivo = tabuleiro1.matriz[x][y];
/*     */     
/* 601 */     int seg = 0;
/*     */     
/* 603 */     for (int i = 1; i <= ncasas; i++) {
/*     */       
/* 605 */       wx = x + i * dirx;
/*     */       
/* 607 */       wy = y + i * diry;
/*     */       
/* 609 */       if (wx > 7 || wx < 0 || wy > 7 || wy < 0) {
/* 610 */         return false;
/*     */       }
/* 612 */       int temp = positivo * tabuleiro1.matriz[wx][wy];
/*     */       
/* 614 */       if (temp > 0) {
/* 615 */         return false;
/*     */       }
/* 617 */       if (temp == 0) {
/* 618 */         seg = 0;
/*     */       }
/*     */       else {
/*     */         
/* 622 */         tabuleiro2.matriz[wx][wy] = 0;
/*     */         
/* 624 */         if (seg == 1) {
/* 625 */           return false;
/*     */         }
/* 627 */         seg = 1;
/*     */ 
/*     */         
/* 630 */         if (Math.abs(tabuleiro1.matriz[wx][wy]) > 1) {
/* 631 */           score.valor += 51;
/*     */         } else {
/*     */           
/* 634 */           score.valor += 50;
/*     */         } 
/*     */       } 
/*     */     } 
/* 638 */     wx = x + ncasas * dirx;
/* 639 */     wy = y + ncasas * diry;
/* 640 */     tabuleiro2.matriz[wx][wy] = tabuleiro2.matriz[x][y];
/* 641 */     tabuleiro2.matriz[x][y] = 0;
/* 642 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void jogar_bem(Tabuleiro t1, int nivel, Tabuleiro t2, int jog) {
/* 649 */     Lista_jogadas candidatos = new Lista_jogadas();
/*     */     
/* 651 */     float melhor = 0.0F, alfa = -20000.0F, beta = 20000.0F;
/*     */     
/* 653 */     int melhor_indice = 0;
/*     */     
/* 655 */     t1.Lista_admissiveis(t1, jog, candidatos);
/*     */     
/* 657 */     if (candidatos.movimentos.size() > 1)
/*     */     {
/* 659 */       for (int i = 0; i < candidatos.movimentos.size(); i++) {
/*     */         
/* 661 */         t1.copia(t2);
/*     */         
/* 663 */         aplica((Vector<?>) candidatos.movimentos.elementAt(i), t2);
/*     */         
/* 665 */         float valor = minimizar(t2, alfa, beta, nivel - 1, jog);
/*     */         
/* 667 */         if (i == 0) {
/* 668 */           melhor = valor;
/* 669 */           melhor_indice = 0;
/*     */         } 
/*     */         
/* 672 */         if (valor > melhor) {
/* 673 */           melhor = valor;
/* 674 */           melhor_indice = i;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 683 */     t1.copia(t2);
/*     */     
/* 685 */     aplica((Vector<?>) candidatos.movimentos.elementAt(melhor_indice), t2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float minimizar(Tabuleiro t1, float alfa, float beta, int nivel, int jog) {
/* 694 */     Tabuleiro t2 = new Tabuleiro();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 699 */     Lista_jogadas candidatos = new Lista_jogadas();
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
/* 713 */     t1.Lista_admissiveis(t1, -jog, candidatos);
/*     */     
/* 715 */     if (candidatos.movimentos.size() == 0) {
/* 716 */       return 10000.0F;
/*     */     }
/* 718 */     for (int i = 0; i < candidatos.movimentos.size(); i++) {
/*     */ 
/*     */ 
/*     */       
/* 722 */       if (nivel <= 0 && 
/*     */         
/* 724 */         !((Boolean)candidatos.instavel.elementAt(i)).booleanValue()) {
/* 725 */         return t1.avalia(jog);
/*     */       }
/* 727 */       t1.copia(t2);
/*     */       
/* 729 */       aplica((Vector<?>) candidatos.movimentos.elementAt(i), t2);
/*     */       
/* 731 */       float valor = maximizar(t2, alfa, beta, nivel - 1, jog);
/*     */       
/* 733 */       if (valor < beta) {
/* 734 */         beta = valor;
/*     */       }
/* 736 */       if (beta <= alfa) {
/* 737 */         return alfa;
/*     */       }
/*     */     } 
/*     */     
/* 741 */     return beta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float maximizar(Tabuleiro t1, float alfa, float beta, int nivel, int jog) {
/* 750 */     Tabuleiro t2 = new Tabuleiro();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 755 */     Lista_jogadas candidatos = new Lista_jogadas();
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
/* 769 */     t1.Lista_admissiveis(t1, jog, candidatos);
/*     */     
/* 771 */     if (candidatos.movimentos.size() == 0) {
/* 772 */       return -10000.0F;
/*     */     }
/* 774 */     for (int i = 0; i < candidatos.movimentos.size(); i++) {
/*     */ 
/*     */ 
/*     */       
/* 778 */       if (nivel <= 0 && 
/*     */         
/* 780 */         !((Boolean)candidatos.instavel.elementAt(i)).booleanValue()) {
/* 781 */         return t1.avalia(jog);
/*     */       }
/* 783 */       t1.copia(t2);
/*     */       
/* 785 */       aplica((Vector<?>) candidatos.movimentos.elementAt(i), t2);
/*     */       
/* 787 */       float valor = minimizar(t2, alfa, beta, nivel - 1, jog);
/*     */       
/* 789 */       if (alfa < valor) {
/* 790 */         alfa = valor;
/*     */       }
/* 792 */       if (alfa >= beta) {
/* 793 */         return beta;
/*     */       }
/*     */     } 
/*     */     
/* 797 */     return alfa;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float avalia(int jog) {
/* 805 */     int a = 0, b = 0, tot = 0, defa = 0, defb = 0;
/*     */     
/* 807 */     float r = 0.0F;
/*     */     
/* 809 */     for (int i = 0; i < 8; i++) {
/*     */ 
/*     */ 
/*     */       
/* 813 */       for (int j = 0; j < 8; j++) {
/*     */ 
/*     */ 
/*     */         
/* 817 */         if ((i + j) % 2 != 1 && this.matriz[i][j] != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 822 */           if (i == 0 && this.matriz[i][j] == 1) {
/* 823 */             defa++;
/*     */           }
/* 825 */           if (i == 7 && this.matriz[i][j] == -1) {
/* 826 */             defb++;
/*     */           }
/* 828 */           if (i == 1 && (j == 3 || j == 5)) {
/* 829 */             defa++;
/*     */           }
/* 831 */           if (i == 2 && j == 4) {
/* 832 */             defa++;
/*     */           }
/* 834 */           if (i == 6 && (j == 2 || j == 4)) {
/* 835 */             defb++;
/*     */           }
/* 837 */           if (i == 5 && j == 3) {
/* 838 */             defb++;
/*     */           }
/* 840 */           if (this.matriz[i][j] == 1) {
/* 841 */             tot++;
/* 842 */             a++;
/*     */           } 
/*     */           
/* 845 */           if (this.matriz[i][j] == 2) {
/* 846 */             tot += 3;
/* 847 */             a += 3;
/*     */           } 
/*     */           
/* 850 */           if (this.matriz[i][j] == -1) {
/* 851 */             tot++;
/* 852 */             b++;
/*     */           } 
/*     */           
/* 855 */           if (this.matriz[i][j] == -2) {
/* 856 */             tot += 3;
/* 857 */             b += 3;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 864 */     r = (jog * (6 * (a - b) + defa - defb)) / (
/* 865 */       4 * tot + defa + defb);
/*     */ 
/*     */ 
/*     */     
/* 869 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void aplica(Vector<?> movimentos, Tabuleiro tabuleiro1) {
/* 879 */     if (movimentos.size() == 0) {
/*     */       return;
/*     */     }
/* 882 */     int x1 = ((Par)movimentos.elementAt(0)).x;
/* 883 */     int y1 = ((Par)movimentos.elementAt(0)).y;
/* 884 */     int guarda = tabuleiro1.matriz[x1][y1];
/*     */     
/* 886 */     for (int i = 0; i < movimentos.size() - 1; i++) {
/*     */       int ix, iy;
/* 888 */       x1 = ((Par)movimentos.elementAt(i)).x;
/* 889 */       y1 = ((Par)movimentos.elementAt(i)).y;
/*     */       
/* 891 */       int j = ((Par)movimentos.elementAt(i + 1)).x;
/* 892 */       int k = ((Par)movimentos.elementAt(i + 1)).y;
/*     */       
/* 894 */       if (j > x1) {
/* 895 */         ix = 1;
/*     */       } else {
/* 897 */         ix = -1;
/*     */       } 
/* 899 */       if (k > y1) {
/* 900 */         iy = 1;
/*     */       } else {
/* 902 */         iy = -1;
/*     */       } 
/* 904 */       for (int cx = x1, cy = y1; cx != j; cx += ix, cy += iy)
/*     */       {
/* 906 */         tabuleiro1.matriz[cx][cy] = 0;
/*     */       }
/* 908 */       tabuleiro1.matriz[j][k] = guarda;
/*     */     } 
/*     */ 
/*     */     
/* 912 */     int x2 = ((Par)movimentos.elementAt(movimentos.size() - 1)).x;
/*     */     
/* 914 */     int y2 = ((Par)movimentos.elementAt(movimentos.size() - 1)).y;
/*     */ 
/*     */ 
/*     */     
/* 918 */     if (Math.abs(tabuleiro1.matriz[x2][y2]) == 2) {
/*     */       return;
/*     */     }
/* 921 */     if (guarda > 0 && x2 == 7) {
/* 922 */       tabuleiro1.matriz[x2][y2] = tabuleiro1.matriz[x2][y2] * 2;
/*     */     }
/* 924 */     if (guarda < 0 && x2 == 0)
/* 925 */       tabuleiro1.matriz[x2][y2] = tabuleiro1.matriz[x2][y2] * 2; 
/*     */   }
/*     */ }


/* Location:              D:\Downloads\Damas Em Java.jar!\damas\Tabuleiro.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */