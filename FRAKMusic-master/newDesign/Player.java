import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

/**
 * <h1>Player.java</h1>
 * <p>
 * This class will make the layout and run all the method we will add.
 * </p>
 * 
 * @author Andre Frisk, Fredrik Kortetjarvi, Kristoffer Guachalla, Rohullah
 *         Khorami
 * @version 1.0
 */
public class Player extends javax.swing.JFrame {
        private javax.swing.JLabel addButton;
        private javax.swing.JLabel currentTime;
        private javax.swing.JLabel endTime;
        private javax.swing.JPanel holdButton;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel12;
        private javax.swing.JPanel jPanel13;
        private javax.swing.JPanel jPanel16;
        private javax.swing.JPanel jPanel17;
        private javax.swing.JPanel jPanel18;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel7;
        private javax.swing.JScrollPane musicList;
        private javax.swing.JTextArea musicListBox;
        private javax.swing.JPanel musicListPanel;
        private javax.swing.JLabel nextButton;
        private javax.swing.JLabel offButton;
        private javax.swing.JLabel pauseButton;
        private javax.swing.JLabel playButton;
        private javax.swing.JProgressBar progressBar;
        private javax.swing.JScrollPane queueList;
        private javax.swing.JTextArea queueListBox;
        private javax.swing.JPanel queuePanel;
        private javax.swing.JButton searchButton;
        private javax.swing.JTextField searchField;
        private javax.swing.JLabel settingsButton;
        private javax.swing.JButton sortArtistButton;
        private javax.swing.JButton sortSongButton;
        private javax.swing.JLabel stopButton;
        final static File folder = new File("/home/fredrik/FRAKMusic/Music");
        private Hash addedList = new Hash();
        private PlayerItem musicItem;
        private ArrayQueue<PlayerItem> queue = new ArrayQueue();
        private PlayButtons buttons;

        public Player() {
                gui();
        }

        public void gui() {
                jPanel2 = new javax.swing.JPanel();
                holdButton = new javax.swing.JPanel();
                stopButton = new javax.swing.JLabel();
                playButton = new javax.swing.JLabel();
                pauseButton = new javax.swing.JLabel();
                nextButton = new javax.swing.JLabel();
                addButton = new javax.swing.JLabel();
                jPanel7 = new javax.swing.JPanel();
                jPanel16 = new javax.swing.JPanel();
                offButton = new javax.swing.JLabel();
                jPanel17 = new javax.swing.JPanel();
                settingsButton = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                jPanel12 = new javax.swing.JPanel();
                jLabel14 = new javax.swing.JLabel();
                jPanel13 = new javax.swing.JPanel();
                progressBar = new javax.swing.JProgressBar();
                currentTime = new javax.swing.JLabel();
                endTime = new javax.swing.JLabel();
                musicListPanel = new javax.swing.JPanel();
                musicList = new javax.swing.JScrollPane();
                musicListBox = new javax.swing.JTextArea();
                jLabel9 = new javax.swing.JLabel();
                sortArtistButton = new javax.swing.JButton();
                sortSongButton = new javax.swing.JButton();
                queuePanel = new javax.swing.JPanel();
                queueList = new javax.swing.JScrollPane();
                queueListBox = new javax.swing.JTextArea();
                jLabel10 = new javax.swing.JLabel();
                jPanel18 = new javax.swing.JPanel();
                searchField = new javax.swing.JTextField();
                searchButton = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setBackground(new java.awt.Color(25, 25, 25));
                setMaximumSize(new java.awt.Dimension(1000, 600));
                setPreferredSize(new java.awt.Dimension(1060, 630));

                jPanel2.setMaximumSize(new java.awt.Dimension(1000, 100));
                jPanel2.setMinimumSize(new java.awt.Dimension(1000, 100));
                jPanel2.setPreferredSize(new java.awt.Dimension(1000, 100));

                stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("stop.png"))); // NOI18N
                stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                stopButtonMouseClicked(evt);
                        }
                });

                playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("play.png"))); // NOI18N
                playButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                try{
                                        playButtonMouseClicked(evt);
                                }catch (LineUnavailableException e){
                                        e.fillInStackTrace();
                                }catch(IOException e){
                                        e.fillInStackTrace();
                                }catch (UnsupportedAudioFileException e){
                                        e.fillInStackTrace();
                                }
                        }
                });

                pauseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("pause.png"))); // NOI18N
                pauseButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                pauseButtonMouseClicked(evt);
                        }
                });

                nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("next.png"))); // NOI18N
                nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                try{
                                        nextButtonMouseClicked(evt);
                                }catch (UnsupportedAudioFileException e){
                                        e.fillInStackTrace();
                                }catch(IOException e){
                                        e.fillInStackTrace();
                                }catch (LineUnavailableException e){
                                        e.fillInStackTrace();
                                }
                        }
                });

                addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("mount.png"))); // NOI18N
                addButton.setMaximumSize(new java.awt.Dimension(100, 100));
                addButton.setMinimumSize(new java.awt.Dimension(100, 100));
                addButton.setPreferredSize(new java.awt.Dimension(100, 100));
                addButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                try{
                                        addButtonMouseClicked(evt);
                                }catch(UnsupportedAudioFileException e){
                                        e.fillInStackTrace();
                                }catch(IOException e ){
                                        e.fillInStackTrace();

                                }catch (LineUnavailableException e){
                                        e.fillInStackTrace();
                                }
                        }
                });

                javax.swing.GroupLayout holdButtonLayout = new javax.swing.GroupLayout(holdButton);
                holdButton.setLayout(holdButtonLayout);
                holdButtonLayout.setHorizontalGroup(holdButtonLayout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, holdButtonLayout
                                                .createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(stopButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(playButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pauseButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nextButton).addGap(18, 18, 18)));
                holdButtonLayout.setVerticalGroup(holdButtonLayout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(holdButtonLayout.createSequentialGroup().addGroup(holdButtonLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(stopButton).addComponent(playButton)
                                                .addComponent(pauseButton).addComponent(nextButton)
                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(jPanel2Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup().addGap(215, 215, 215)
                                                .addComponent(holdButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(holdButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                jPanel7.setPreferredSize(new java.awt.Dimension(1000, 50));

                offButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("off.png"))); // NOI18N
                offButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                offButtonMouseClicked(evt);
                        }
                });

                settingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("settings.png"))); // NOI18N
                settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                try{
                                        settingsButtonMouseClicked(evt);
                                }catch(UnsupportedAudioFileException e){
                                        e.fillInStackTrace();
                                }catch(IOException e){
                                        e.fillInStackTrace();
                                }catch (LineUnavailableException e){
                                        e.fillInStackTrace();
                                }
                        }
                });

                javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                jPanel17.setLayout(jPanel17Layout);
                jPanel17Layout.setHorizontalGroup(
                                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel17Layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(settingsButton)));
                jPanel17Layout.setVerticalGroup(
                                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(settingsButton));

                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                jPanel16.setLayout(jPanel16Layout);
                jPanel16Layout.setHorizontalGroup(jPanel16Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout
                                                .createSequentialGroup().addContainerGap()
                                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(offButton)));
                jPanel16Layout.setVerticalGroup(jPanel16Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createSequentialGroup().addGroup(jPanel16Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(offButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE)));

                jLabel13.setText("FRAKMusic");

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(jPanel7Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout
                                                .createSequentialGroup().addContainerGap().addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                jPanel7Layout.setVerticalGroup(jPanel7Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createSequentialGroup().addContainerGap()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)));

                jPanel12.setPreferredSize(new java.awt.Dimension(250, 250));

                jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("logo.png"))); // NOI18N

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel12Layout.createSequentialGroup().addComponent(jLabel14)
                                                                .addGap(0, 0, Short.MAX_VALUE)));
                jPanel12Layout.setVerticalGroup(
                                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel12Layout.createSequentialGroup().addComponent(jLabel14)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                currentTime.setText("currenttime");

                endTime.setText("timeend");

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(jPanel13Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel13Layout.createSequentialGroup().addGap(27, 27, 27)
                                                .addComponent(currentTime).addGap(34, 34, 34)
                                                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 800,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32).addComponent(endTime)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)));
                jPanel13Layout.setVerticalGroup(jPanel13Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout
                                                .createSequentialGroup().addContainerGap()
                                                .addGroup(jPanel13Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                jPanel13Layout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(endTime))
                                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                                                .addComponent(currentTime)
                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                .addComponent(progressBar,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addContainerGap()));

                musicListBox.setEditable(false);
                musicList.setViewportView(musicListBox);

                jLabel9.setText("Music List");

                sortArtistButton.setText("sort artist");

                sortSongButton.setText("sort Song");
                sortSongButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                sortSongButtonActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout musicListPanelLayout = new javax.swing.GroupLayout(musicListPanel);
                musicListPanel.setLayout(musicListPanelLayout);
                musicListPanelLayout.setHorizontalGroup(musicListPanelLayout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(musicListPanelLayout.createSequentialGroup().addContainerGap()
                                                .addGroup(musicListPanelLayout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(musicList)
                                                                .addGroup(musicListPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel9)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                136, Short.MAX_VALUE)
                                                                                .addComponent(sortArtistButton)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(sortSongButton)))
                                                .addContainerGap()));
                musicListPanelLayout.setVerticalGroup(musicListPanelLayout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, musicListPanelLayout
                                                .createSequentialGroup().addContainerGap()
                                                .addGroup(musicListPanelLayout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel9).addComponent(sortArtistButton)
                                                                .addComponent(sortSongButton))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12,
                                                                Short.MAX_VALUE)
                                                .addComponent(musicList, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap()));

                queuePanel.setPreferredSize(new java.awt.Dimension(375, 0));

                queueListBox.setEditable(false);
                queueListBox.setKeymap(null);
                queueList.setViewportView(queueListBox);

                jLabel10.setText("Queue");

                javax.swing.GroupLayout queuePanelLayout = new javax.swing.GroupLayout(queuePanel);
                queuePanel.setLayout(queuePanelLayout);
                queuePanelLayout.setHorizontalGroup(queuePanelLayout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(queuePanelLayout.createSequentialGroup().addContainerGap()
                                                .addGroup(queuePanelLayout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(queueList,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(queuePanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel10)
                                                                                .addGap(0, 314, Short.MAX_VALUE)))
                                                .addContainerGap()));
                queuePanelLayout.setVerticalGroup(queuePanelLayout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, queuePanelLayout
                                                .createSequentialGroup().addContainerGap().addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(queueList, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap()));

                searchField.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                searchFieldActionPerformed(evt);
                        }
                });

                searchButton.setText("search");
                searchButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                              try {  searchButtonActionPerformed(evt);
                              
                              }                           	  
                              catch(UnsupportedAudioFileException e){
                                  e.fillInStackTrace();
                          }catch(IOException e){
                                  e.fillInStackTrace();
                          }catch (LineUnavailableException e){
                                  e.fillInStackTrace();
                          }
                        }
                });

                javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                jPanel18.setLayout(jPanel18Layout);
                jPanel18Layout.setHorizontalGroup(jPanel18Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel18Layout.createSequentialGroup().addContainerGap()
                                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchButton)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)));
                jPanel18Layout.setVerticalGroup(jPanel18Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel18Layout.createSequentialGroup().addGap(43, 43, 43)
                                                .addGroup(jPanel18Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(searchField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(searchButton))
                                                .addContainerGap(59, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel2,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup().addGroup(layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1058,
                                                                Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(musicListPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(queuePanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jPanel12,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addContainerGap())
                                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jPanel12,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(queuePanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                260, Short.MAX_VALUE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(musicListPanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                getAccessibleContext().setAccessibleDescription("");

                pack();
                // </editor-fold>//GEN-END:initComponen
        }
/**
 * this part of the code is to sense when a button is pressed on the gui
 * @param evt goodle
 * @throws UnsupportedAudioFileException ??
 * @throws IOException ??
 * @throws LineUnavailableException ??
 * @throws IOException ??
 */
        private void playButtonMouseClicked(java.awt.event.MouseEvent evt)throws UnsupportedAudioFileException, IOException, LineUnavailableException,IOException {
                // TODO add your handling code here:
                buttons.play();
        }

        private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {
                // TODO add your handling code here:
                buttons.stop();
        }

        private void pauseButtonMouseClicked(java.awt.event.MouseEvent evt) {
                // TODO add your handling code here:
                buttons.pause();
        }

        private void nextButtonMouseClicked(java.awt.event.MouseEvent evt)throws UnsupportedAudioFileException,IOException, LineUnavailableException {
                // TODO add your handling code here:
                buttons.next();
        }

        private void addButtonMouseClicked(java.awt.event.MouseEvent evt)throws UnsupportedAudioFileException,IOException, LineUnavailableException {
                //TODO buttons.add();
                buttons = new PlayButtons();
                
        }

        private void sortSongButtonActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void searchButtonActionPerformed(java.awt.event.ActionEvent evt)throws UnsupportedAudioFileException,IOException, LineUnavailableException {
                // TODO add your handling code here:
                        String name = searchField.getText();
                        System.out.print(name);
        		search(name);
        }

        private void settingsButtonMouseClicked(java.awt.event.MouseEvent evt)throws UnsupportedAudioFileException,IOException, LineUnavailableException {
                // TODO add your handling code here:
                adding(folder);
        }

        private void offButtonMouseClicked(java.awt.event.MouseEvent evt) {
                // TODO add your handling code here:
        }

        public static void main(String[] args) {
                new Player().setVisible(true);

        }

        /**
         * add the music into the program
         * 
         * @param source is the map all the music is in.
         */
        public void adding(final File source) {
                final File[] listOfFiles = source.listFiles();

                for (File file : listOfFiles) {
                        if (file.isDirectory()) {
                                adding(file);
                        } else {
                                
                                musicItem = new PlayerItem(file);
                                musicListBox.append(musicItem.getArtist()+" "+ musicItem.getName()+" "+musicItem.getTime()+ "\n");
                                addedList.add(musicItem);
                                queue.enqueue(musicItem);
                                

                        }
                }
        }
        /**
         * Search in hashtable
         * @param file is the file that will be added to the hash
         * @throws UnsupportedAudioFileException if is not a supported audio file
         * @throws IOException ????
         * @throws LineUnavailableException ??
         */
        public void search(String file) throws UnsupportedAudioFileException,IOException, LineUnavailableException{
                PlayerItem song = addedList.findMatch(file);
        	buttons.search(song);
        }

}