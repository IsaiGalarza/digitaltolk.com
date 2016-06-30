package com.digitaltolk.qa.common;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

import net.coobird.thumbnailator.Thumbnails;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

import com.common.oTest.oTestFindAllPageDeclarationPages;
import com.common.oTest.oTestFindAllTestNGTests;
import com.data.oTest.oTestPageDeclarationDirectoryList;
import com.data.oTest.oTestTestNGDirectoryList;


@SuppressWarnings("unused")
public class digitaltolkPageDeclarationViewer extends JPanel implements ActionListener,MouseListener, TreeModelListener,TreeSelectionListener {

	private static final long serialVersionUID = 2952023956247439024L;
	private File cPath = null;
	
	private MyTreeTableModel treeTableModel = null;
	private JXTreeTable treeTable = null;
	private JPanel testOutPutPanel = null;
	private JPanel actionPanel = null;
	private JLabel card = null;
	MyTreeNode selectedNode = null;
	MyTreeNode parentNode = null;
	String[] seleniumElements = null;
	private String osName = null;
	private String pathName = null;
	private String resourcePathName = null;
	private String osPathDivider = null;
	private String notFoundImage = "notFoundScreen.png";
	
	
	private digitaltolkPageDeclarationViewer(){
		
		cPath = new File(".");
		osName = new String(System.getProperty("os.name")); // Mac OS X, Windows 7
		
		
		  if(osName.contains("Windows")){
			  pathName = new String (getCanonicalPath()+"\\resources\\pageDeclarationScreenImages\\");
			  resourcePathName = new String(getCanonicalPath()+"\\resources\\elance\\");
			  osPathDivider = new String("\\");
	    	  
			}else{ // Mac and linux use the same path 
				pathName = new String(getCanonicalPath()+"/resources/pageDeclarationScreenImages/");
				resourcePathName  = new String(getCanonicalPath()+"/resources/elance/");
				osPathDivider = new String("/");
			}
	  
		  
		setLayout( new GridLayout(1 /* rows */, 2/* cols */) );
		
		treeTableModel = new MyTreeTableModel();
		treeTableModel.addTreeModelListener(this);
		
		treeTable = new JXTreeTable( treeTableModel );
		
		treeTable.addMouseListener(this);
		
		treeTable.getSelectionModel().setSelectionMode
        (TreeSelectionModel.SINGLE_TREE_SELECTION);

		//Listen for when the selection changes.
		treeTable.addTreeSelectionListener(this);
		
		// Build the tree table panel
		JPanel treeTablePanel = new JPanel( new BorderLayout() );
		treeTablePanel.add( new JScrollPane( treeTable ) );
		treeTablePanel.setBorder( new TitledBorder("Elance Pages") );
		//
		// set the text area
		//
		//
		// Source Code panel
		//
		testOutPutPanel = new JPanel();
		testOutPutPanel.setLayout( new GridLayout(1,1));
		testOutPutPanel.setBorder( new TitledBorder("Elance Page Information") );
		
		
		actionPanel = new JPanel();
		actionPanel.setLayout( new GridLayout(1,1));
		actionPanel.setBorder( new TitledBorder("Page Image") );
		//
		// add the  image to the page
		//
		File path1th = null;
		try {
			
			path1th = new File(pathName+notFoundImage);
			// /Users/davidwramer/Perforce/dr-elance/EOL-internal/tools/QA/oTest/eLance/resources/pageDeclarationScreenSnaps/notFoundScreen.png
			// /Users/davidwramer/Perforce/dr-elance/EOL-internal/tools/QA/oTest/eLance/resources/pageDeclarationScreenImages
			// /Users/davidwramer/Perforce/dr-elance/EOL-internal/tools/QA/oTest/eLance/resources/pageDeclarationScreenSnaps/visitorSiteLandingPage.png
			if (path1th.isFile()){
				if (/*path1th.isFile() && */ path1th.canRead()) {
					BufferedImage img1 = null;
					
					img1 = ImageIO.read(path1th);
					ImageIcon ii = new ImageIcon(img1);
					card = new JLabel(ii);
					card.setName("oDesk");
					JPanel cardPanel = new JPanel();
					cardPanel.setLayout( new GridLayout(1,1));
					cardPanel.setBorder( new TitledBorder("Double Click to View") );
					cardPanel.add(card);
					actionPanel.add(card/*cardPanel*/);
					card.addMouseListener(this);
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		testOutPutPanel.add(actionPanel);
		// 
		// Add the tabs to the JFrame
		//
		add( treeTablePanel );
		add(testOutPutPanel);
	
	}// constructor
	/**
	 * findProperty() function to find a certain property
	 * 
	 * @param String pageDeclarationName - page name
	 * @param String directory directory of the property file
	 * @param String propertyName - property to find
	 * @return String found property
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	private String findProperty(String pageDeclarationName,String directory,String propertyName){
		String property = null;
		Properties prop = new Properties();
		 if(pageDeclarationName.contains(".java"))
			 pageDeclarationName = new String(pageDeclarationName.substring(0, (pageDeclarationName.length()-5)));
		try {
			
			String propertiesFile = new String(resourcePathName+directory+osPathDivider+pageDeclarationName+".properties");
			prop.load(new FileInputStream(propertiesFile));
			property = new String(prop.getProperty(propertyName));
		} catch (FileNotFoundException e1) {
			
			System.out.println("findProperty: no properties found ");
		} catch (IOException e1) {
			
			System.out.println("findProperty: no properties found");
		}	
			
		return property;
	}//findProperty
	/**
	 * loadPropertiesForFile() function returns all properties for a given page declaration
	 * 
	 * @param String pageDeclarationName - page name
	 * @param String directory directory of the property file
	 * @return String[] list of all property
	 * @exception None.
	 * @author davidwramer
	 * @version 1.0
	 */
	private String [] loadPropertiesForFile(String pageDeclarationName,String directory){
		String [] list = null;
		String keyValuePair = new String("Selenium Elements | ----------------- |");
		Properties prop = new Properties();
		//FileInputStream propFile = null;
		
		 if(pageDeclarationName.contains(".java"))
			 pageDeclarationName = new String(pageDeclarationName.substring(0, (pageDeclarationName.length()-5)));
		try {
			
			String propertiesFile = new String(resourcePathName+directory+osPathDivider+pageDeclarationName+".properties");
			
			//   /Users/davidwramer/Perforce/dr-elance/EOL-internal/tools/QA/oTest/eLance/resources/elance/visitor/visitorSiteLandingPage.properties
			//   /Users/davidwramer/Perforce/dr-elance/EOL-internal/tools/QA/oTest/eLance/resources/elance/visitor
			
			prop.load(new FileInputStream(propertiesFile));
			
			for(String key : prop.stringPropertyNames()) {
				  String value = prop.getProperty(key);
				  System.out.println(key + "=" + value);
				  keyValuePair = new String(keyValuePair.concat(key + "=" + value+" | "));
			}
			String[] s_tokens = keyValuePair.split("\\|");
	    	//
	    	// Make sure there are no "|" embedded in the string
	    	//
			list = new String[s_tokens.length];
			int cnt = 0;
			for(String str : s_tokens){
				
				list[cnt++] = new String (str);
			}
			
		} catch (FileNotFoundException e1) {
			list = null;
			System.out.println("loadPropertiesForFile: no properties found ");
		} catch (IOException e1) {
			list = null;
			System.out.println("loadPropertiesForFile: no properties found");
		}
		return list;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}//actionPerformed
	/**
	 * This Class changeImage().
	 * <br>changeImage for the test display panel
	 * <br> 
	 * <br>
	 * @param pageName
	 * @return None.
	 * @exception None.
	 * @see oTestExecutionPanel
	 * @author davidwramer
	 * @version 1.0
	 */
	private void changeImage(String pageName){
		JLabel newCard = null;
		Cursor originalCursor = this.getCursor();
		this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
		 //
		 // Strip the .java from the name
		 //
		 if(pageName.contains(".java"))
			 pageName = new String(pageName.substring(0, (pageName.length()-5)));
		 try {
			File path1th = null;
			
			path1th = new File(pathName+pageName+"-TN.png");
			
			
			BufferedImage img1 = null;
			if (path1th.isFile() && path1th.canRead()) {
				img1 = ImageIO.read(path1th);
			}else{
				File fullSize = null;
				
				fullSize = new File(pathName+pageName+".png");
				
				if (fullSize.isFile() && fullSize.canRead()) {
					Thumbnails.of(fullSize).size(320,320).toFile(path1th);
				}else{
					
					fullSize = new File(pathName+notFoundImage);
					Thumbnails.of(fullSize).size(320,320).toFile(path1th);
					pageName = new String("notFoundScreen");
				}
		    	img1 = ImageIO.read(path1th);
			}
			
			ImageIcon ii = new ImageIcon(img1);
			newCard = new JLabel(ii);
			newCard.setName(pageName);
			newCard.addMouseListener(this);
			actionPanel.removeAll();
			actionPanel.add(newCard);
			actionPanel.repaint();
			actionPanel.revalidate();
			newCard.repaint();
			newCard.revalidate();
			actionPanel.invalidate();
			newCard.invalidate();
			testOutPutPanel.invalidate();
			testOutPutPanel.revalidate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("changeImage Function failed to open: ");
			e.printStackTrace();
		}
		this.setCursor(originalCursor);	
	}
	/**
	 * This Class MyTreeTableModel().
	 * <br>AbstractTreeTableModel for the test display panel
	 * <br> 
	 * <br>
	 * @return None.
	 * @exception None.
	 * @see oTestExecutionPanel
	 * @author davidwramer
	 * @version 1.0
	 */
	public class MyTreeTableModel extends AbstractTreeTableModel
	{
		private MyTreeNode myroot;
		protected EventListenerList listenerList = new EventListenerList();
		oTestFindAllPageDeclarationPages digDirectoryHelper;
		private String osName = null;
		
		@SuppressWarnings("unchecked")
		public MyTreeTableModel()
		{
			
			osName = new String(System.getProperty("os.name")); // Mac OS X, Windows 7
			
			Path digThisDirectory;
			
			if(osName.contains("Windows")){
				digThisDirectory = Paths.get(getCanonicalPath()+"\\src\\com\\pageDeclarations\\elance");
			}else{ // Mac and linux use the same path 
				digThisDirectory = Paths.get(getCanonicalPath()+"/src/com/pageDeclarations/elance");
			}
			
			
			
		
			Path searchMe = Paths.get("visitorSiteLandingPage.java");
			digDirectoryHelper = new oTestFindAllPageDeclarationPages();
			digDirectoryHelper.searchThis(searchMe);
			@SuppressWarnings("rawtypes")
			EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
			try {
				Files.walkFileTree(digThisDirectory, opts, Integer.MAX_VALUE, digDirectoryHelper);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			ArrayList<oTestPageDeclarationDirectoryList> directoryList = digDirectoryHelper.returnResults();
        
			myroot = new MyTreeNode( "oElance Page Declarations", "Root of all page definitions" );
		
			int listCount = directoryList.size();
		
			for(int cnt = 0; cnt < listCount; cnt++){
			
				oTestPageDeclarationDirectoryList list = (oTestPageDeclarationDirectoryList)directoryList.get(cnt);
				String directory =  new String (list.getDirectoryName());
				
				MyTreeNode subtree = new MyTreeNode(directory, list.getDirectoryDescription());
				if(list != null){
					System.out.println("Directory: " + list.getDirectoryName());
					for(int x =0;x < list.pages.length; x++){
						if(list.pages[x] != null){
							String pageDeclaration =  new String (list.pages[x]);
							System.out.println("Page Declarations: " + pageDeclaration);
							subtree.getChildren().add( new MyTreeNode( pageDeclaration, findProperty(pageDeclaration,list.getDirectoryName(),"pageURL")) );
						} 
					}
				}
				myroot.getChildren().add( subtree );
			}
			
		}
		public oTestFindAllPageDeclarationPages getDirectoryData(){
			return digDirectoryHelper;
		}
		 //
        // Default implementations for methods in the TreeModel interface.
        //
        public void addTreeModelListener(final TreeModelListener l) {
                listenerList.add(TreeModelListener.class, l);
        }
		@Override
		public int getColumnCount() 
		{
			return 3;
		}
		
		@Override
		public String getColumnName( int column )
		{
			switch( column )
			{
			case 0: return "Elance Page information";
			case 1: return "URL";
			case 2: return "Number Of sub pages";
			default: return "Unknown";
			}
		}

		@Override
		public Object getValueAt( Object node, int column ) 
		{
			// System.out.println( "getValueAt: " + node + ", " + column );
			MyTreeNode treenode = ( MyTreeNode )node;
			switch( column )
			{
			case 0: return treenode.getName();
			case 1: return treenode.getDescription();
			case 2: return treenode.getChildren().size();
			default: return "Unknown";
			}
		}

		@Override
		public Object getChild( Object node, int index ) 
		{
			MyTreeNode treenode = ( MyTreeNode )node;
			return treenode.getChildren().get( index );
		}

		@Override
		public int getChildCount( Object parent ) 
		{
			MyTreeNode treenode = ( MyTreeNode )parent;
			return treenode.getChildren().size();
		}

		@Override
		public int getIndexOfChild( Object parent, Object child ) 
		{
			MyTreeNode treenode = ( MyTreeNode )parent;
			for( int i=0; i>treenode.getChildren().size(); i++ )
			{
				if( treenode.getChildren().get( i ) == child )
				{
					return i;
				}
			}

			// TODO Auto-generated method stub
			return 0;
		}
		
		 public boolean isLeaf( Object node )
		 {
			 MyTreeNode treenode = ( MyTreeNode )node;
			 if( treenode.getChildren().size() > 0 )
			 {
				 return false;
			 }
			 return true;
		 }
		 
		 @Override
		 public Object getRoot()
		 {
			 return myroot;
		 }
		 
		 public Object  findObjectInTree(int row){
			
			 int cnt = 0;
			 List<MyTreeNode> rootList = myroot.getChildren();
			 //
			 // Children of Root
			 //
			 for(int x = 1; x < rootList.size();x++){
				 
				 MyTreeNode childItem = (MyTreeNode) rootList.get(x);
				 cnt++;
				 if(row == cnt) return null;
				 List<MyTreeNode> childList = childItem.getChildren();
				 //
				 // Grand children of Root
				 //
				
				 for(int y = 0; y < childList.size();y++){
					 Object sibling = childList.get(y);
					 cnt++;
					 if(row == cnt) return sibling;
				 }
			 }
			 
			 return null;
			 
		 }
	}// MyTreeTableModel
	
	class EditableListPanel extends JPanel {

	  
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		EditableListPanel(String[] items) {
	        super( new GridLayout(1 /* rows */, 1/* cols */) );
	        //JComboBox<String> jcb = new JComboBox<String>(items);
	        final JList<String> list = new JList<String>( items );
	        JLabel jlbl = new JLabel("Selenium Elements");
	        // add(jlbl,BorderLayout.CENTER);
	        add( list, BorderLayout.CENTER );
	    }

	}

	class MyTreeNode
	{
		private String name;
		private String description;
		private List<MyTreeNode> children = new ArrayList<MyTreeNode>();
		
		public MyTreeNode() 
		{
		}
		
		public MyTreeNode( String name, String description ) 
		{
			this.name = name;
			this.description = description;
		}
		
		public String getName() 
		{
			return name;
		}
		
		public void setName(String name) 
		{
			this.name = name;
		}
		
		public String getDescription() 
		{
			return description;
		}
		
		public void setDescription(String description) 
		{
			this.description = description;
		}
		
		public List<MyTreeNode> getChildren() 
		{
			return children;
		}
		
		public String toString()
		{
			return "MyTreeNode: " + name + ", " + description;
		}
	}// MyTreeNode

	@Override
	public void treeNodesChanged(TreeModelEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "treeNodesChanged action called");
	}
	@Override
	public void treeNodesInserted(TreeModelEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "treeNodesInserted action called");
	}
	@Override
	public void treeNodesRemoved(TreeModelEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "treeNodesRemoved action called");
	}
	@Override
	public void treeStructureChanged(TreeModelEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "treeStructureChanged action called");
	}
	public void treeNodesStructureChanged(TreeModelEvent e){
		System.out.println( "treeNodesStructureChanged action called");	
	}
	// Called after the tree's structure has drastically changed.
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	 @Override
	 public void mouseClicked(MouseEvent mEvt){
		 if (mEvt.getClickCount() == 2) {
			 System.out.println("double clicked");
			 Component comp  = mEvt.getComponent();
			 
			  if (comp != null && comp instanceof JLabel) {
				  JLabel selectedCard = (JLabel) comp;
			      String imageName = new String(selectedCard.getName());
			      String imageNameThumbNail = new String(selectedCard.getName());
			      System.out.println("double clicked Image : "+ imageName);
			      // 
			      // now open the full image
			      //
			      if(imageName.contains(".java"))
			    	  imageName = new String(imageName.substring(0, (imageName.length()-5)));
	
			      imageName = new String(imageName.concat(".png")); // full size screen snap
			      imageNameThumbNail = new String(imageNameThumbNail.concat("-TN.png")); // full size screen snap
			      try {
			    	  File path1th;
			    	  //
			    	  // Only Create the Thumbnail if it is not created
			    	  //
			    	  File thumbNailImage = new File(pathName+imageNameThumbNail);
			    	  if(!thumbNailImage.exists()){
			    		  File path1 = new File(pathName+imageName);
			    		  Thumbnails.of(path1).size(400,400).toFile(thumbNailImage = new File(pathName+imageNameThumbNail));
			    	  }
			    	  BufferedImage img1 = ImageIO.read(thumbNailImage);
			    	  ImageIcon ii = new ImageIcon(img1);
						
			    	  String[] items = {"No Selenium Elements"};
			    	  //
			    	  // Find all the methods for the pageDeclaration
			    	  //
			    	  EditableListPanel elp = null;
			    	  if(seleniumElements !=null){
			    		  elp = new EditableListPanel(seleniumElements);
			    	  }else{
			    		  elp = new EditableListPanel(items);
			    	  }
			    	  JScrollPane scrollPane = new JScrollPane( elp);
			    	  Dimension d = new Dimension();
			    	  d.setSize(400, 800);
			    	  scrollPane.setPreferredSize(d);
			    	  JComboBox<String> jcb = new JComboBox<String>(items);
		              //JLabel lbl = new JLabel("Selenium Elements");	
		              jcb.setEditable(false);
		              //JOptionPane.showMessageDialog(null, jcb);
			    	  
			    	  JOptionPane.showMessageDialog(null,/*elp*/scrollPane/*nameOfPage*/,"ElancePageVisualizationView",JOptionPane.WARNING_MESSAGE,ii);
					} catch (IOException e) {
						System.err.println("ERROR: building new image: "+ e);
					}
			  }else// JXTreeTablle
				  if (comp != null && comp instanceof JXTreeTable) {
					  TreeSelectionModel tsm = treeTable.getTreeSelectionModel();
					  selectedNode = (MyTreeNode) tsm.getSelectionPath().getLastPathComponent();
					  parentNode = (MyTreeNode) tsm.getSelectionPath().getParentPath().getLastPathComponent();
					  System.out.println("Single click table Element : "+ selectedNode.getName());
					  seleniumElements = loadPropertiesForFile(selectedNode.getName(),parentNode.getName());
					  changeImage(selectedNode.getName());
				  
			  }     
		   	}
		 
	   }//mouseClick
	 /**
		 * getCanonicalPath()
		 * 
		 * @param none
		 * @return String local path to the project
		 * @author davidwramer
		 */
		public String getCanonicalPath(){
			
			if(cPath != null){
				try {
					return(cPath.getCanonicalPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				return null;
		}
	 //
	   	// Inner class for testing on the command line
	   	//
	   	 public static class Test
	   	 {
	   	 	@SuppressWarnings({ "deprecation", "static-access" })
			public static void main(final String[] args){
	   	 		
	   	 	
	   	 	digitaltolkPageDeclarationViewer myDisplay = null;
	   	 	
	   	 	myDisplay = new digitaltolkPageDeclarationViewer();
	   	 	JFrame frame = new JFrame("Elance Page Declaration Viewer");
	   
	   	 	frame.setSize(256,256);
	   	 	frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	   	 	frame.getContentPane().add(myDisplay);
	   	 	frame.pack();
	   	 	frame.show();
	   	 		
	   	 	}
	   	 }
	
}