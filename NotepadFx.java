package HelloWorldFx;

import java.awt.event.ActionListener;
import java.awt.font.NumericShaper.Range;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.security.action.GetBooleanSecurityPropertyAction;

public class NotepadFx extends Application {
	BorderPane theLayout;
	MenuBar bar;
	Menu f;
	Menu e;
	Menu h;
	MenuItem new1;
	MenuItem open1;
	MenuItem save1;
	MenuItem exit1;
	MenuItem undo;
	MenuItem cut;
	MenuItem copy;
	MenuItem paste;
	MenuItem delete;
	MenuItem selectAll;
	MenuItem aboutNote;
	TextArea theArea;
	int flag=0;
	String words;
	SeparatorMenuItem separatorMenuItem;
	SeparatorMenuItem separatorMenuEdit;
	public void init() throws Exception {
		theLayout=new BorderPane();
		bar=new MenuBar();
		separatorMenuItem= new SeparatorMenuItem();
		separatorMenuEdit=new SeparatorMenuItem();
		theArea=new TextArea();
		f=new Menu("File");
		e=new Menu("Edit");
		h=new Menu("Help");
		new1=new MenuItem("New");
		open1=new MenuItem("Open...");
		save1=new MenuItem("Save");
		exit1=new MenuItem("Exit");
		undo=new MenuItem("Undo");
		cut=new MenuItem("Cut");
		copy=new MenuItem("Copy");
		paste=new MenuItem("Paste");
		delete=new MenuItem("Delete");
		selectAll=new MenuItem("Select All");
		aboutNote=new MenuItem("About Notepad");
		super.init();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		f.getItems().add(new1);
		f.getItems().add(open1);
		f.getItems().add(save1);
		f.getItems().add(separatorMenuItem);
		f.getItems().add(exit1);
		e.getItems().add(undo);
		e.getItems().add(separatorMenuEdit);
		e.getItems().add(cut);
		e.getItems().add(copy);
		e.getItems().add(paste);
		e.getItems().add(delete);
		e.getItems().add(selectAll);
		h.getItems().add(aboutNote);
		bar.getMenus().add(f);
		bar.getMenus().add(e);
		bar.getMenus().add(h);
		theLayout.setTop(bar);
		theLayout.setCenter(theArea);
		Scene s=new Scene(theLayout,600,600);
		new1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               theArea.clear();
            }
        });
		open1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            	FileChooser fc = new FileChooser();
            	  fc.setTitle("My File Chooser");
            	  File f = fc.showOpenDialog(primaryStage);
            	try (BufferedReader reader = new BufferedReader(new FileReader(f))) {

                    String line;
                    while ((line = reader.readLine()) != null)
                        theArea.appendText(line+"\n");

                } catch (IOException e) {
                	Alert alert = new Alert(AlertType.INFORMATION, "This App Was Created By Ali-Abdelkreem Ali Ahmed");
					alert.showAndWait();
                    e.printStackTrace();
                }
            }
            
        });
		save1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PrintWriter writer;
				flag=1;
				try {
					FileChooser fc = new FileChooser();
	            	  fc.setTitle("My File Chooser");
	            	  File f = fc.showSaveDialog(primaryStage);
					FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		            fc.getExtensionFilters().add(extFilter);
		            
		            	
					writer = new PrintWriter(f, "UTF-8");
					String words=theArea.getText();
					writer.println(words);
					writer.close();
		            
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
		});
		exit1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				PrintWriter writer;
				if(flag==0) {
					Alert a=new Alert(AlertType.WARNING,"Do you Want to save The File",ButtonType.OK,ButtonType.CANCEL);
					Optional<ButtonType> result = a.showAndWait();
					if(result.get()==ButtonType.CANCEL) {
					Stage sb = (Stage)bar.getScene().getWindow();
					sb.close();//use any one object
					}
					else if(result.get()==ButtonType.OK) {
						try {
							FileChooser fc = new FileChooser();
			            	  fc.setTitle("My File Chooser");
			            	  File f = fc.showSaveDialog(primaryStage);
							FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				            fc.getExtensionFilters().add(extFilter);
				            
				            	
							writer = new PrintWriter(f, "UTF-8");
							String words=theArea.getText();
							writer.println(words);
							writer.close();
				            flag=0;
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
			        
			        
				}
				Stage sb = (Stage)bar.getScene().getWindow();
				sb.close();
				
			}
			
		});
		s.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
		  () {

		        @Override
		        public void handle(KeyEvent t) {
		        	PrintWriter writer;
					if(flag==0) {
						Alert a=new Alert(AlertType.WARNING,"Do you Want to save The File",ButtonType.OK,ButtonType.CANCEL);
						Optional<ButtonType> result = a.showAndWait();
						if(result.get()==ButtonType.CANCEL) {
						Stage sb = (Stage)bar.getScene().getWindow();
						sb.close();//use any one object
						}
						else if(result.get()==ButtonType.OK) {
							try {
								FileChooser fc = new FileChooser();
				            	  fc.setTitle("My File Chooser");
				            	  File f = fc.showSaveDialog(primaryStage);
								FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
					            fc.getExtensionFilters().add(extFilter);
					            
					            	
								writer = new PrintWriter(f, "UTF-8");
								String words=theArea.getText();
								writer.println(words);
								writer.close();
					            flag=0;
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
				        
				        
					}
					
					Stage sb = (Stage)bar.getScene().getWindow();
					sb.close();
		        }
		        
		          
		        
		    });
		undo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				theArea.undo();
			}		
		});
		cut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				words=theArea.getSelectedText();
				theArea.cut();
			}
		});
		copy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				words=theArea.getSelectedText();
				
			}
		});
		copy.setAccelerator(KeyCombination.keyCombination("Ctrl+d"));
		paste.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int i=theArea.getCaretPosition();
				theArea.insertText(i, words);
				
				
			}
		});
		paste.setAccelerator(KeyCombination.keyCombination("Ctrl+l"));
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				theArea.deleteText(theArea.getSelection());
			}
		});
		selectAll.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				theArea.selectAll();
			}
		});
		aboutNote.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.INFORMATION, "This App Was Created By Ali-Abdelkreem Ali Ahmed",ButtonType.OK);
				alert.showAndWait();


			}
			
		});
		
		
		primaryStage.setTitle("Notepad");
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
