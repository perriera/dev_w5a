package org.w4a.server;

import java.io.File;
import org.w0a.HTMLEdit;
import org.w2a.W2AIniFile;
import org.w2a.ngfile.NgFile;
import org.w4a.Main;
import org.w4a.W4AIniFile;
import org.w4a.inventory.IniFileInventory;
import org.w4a.keys.InventoryRequestKey;
import org.w4a.keys.UserRequest;
import org.w4a.requests.UploadRequest;
import org.w4a.requests.UploadRequestLogic;
import org.w4a.tasks.InjectSkeletonPackage;
import org.w4a.tasks.TaskInterface;
import org.w4a.tasks.PrepareIniFileForUse;
import org.w4a.tasks.ProcessPrepareUseCase;

public class Server extends Main implements ServerInterface {

	/**
	 * Server IniFile & Construct
	 */
	
	W4AIniFile iniFile;

	public Server(String filename) throws Exception {
		super(filename);
		this.iniFile = new W4AIniFile(filename);
	}

	public Server(W4AIniFile w4aIniFile) throws Exception {
		super(w4aIniFile.getFilename());
		this.iniFile = w4aIniFile;
	}

	public W4AIniFile getXIniFile() {
		return iniFile;
	}

	/**
	 * Server Setup & Destroy
	 */
	
	public void setup() throws Exception {
		ServerUtils.mkdirs(iniFile.getMainDirectory());
		ServerUtils.mkdirs(iniFile.getIniFilesDirectory());
		ServerUtils.mkdirs(iniFile.getSource());
		ServerUtils.mkdirs(iniFile.getDestination());
		ServerUtils.mkdirs(iniFile.getProcessingDir());
		ServerUtils.mkdirs(iniFile.getResourcesDirectory());
		ServerUtils.mkdirs(iniFile.getDiagnosticsDir());
	}

	public boolean exists() throws Exception {
		// TODO Auto-generated method stub
		return ServerUtils.exists(iniFile.getMainDirectory());
	}
	
	public boolean exists(String filename) throws Exception {
		// TODO Auto-generated method stub
		return ServerUtils.exists(filename);
	}

	public void destroy() throws Exception {
		ServerUtils.rmdirs(iniFile.getMainDirectory());
	}
	
	public void reset() throws Exception {
		destroy();
		setup();
	}
	
	/**
	 * Inventory copy & test ... 
	 */
	
	public void copyToInventory(W2AIniFile w2aIniFile) throws Exception {
		String path = w2aIniFile.getFilename();
		String filename = ServerUtils.getFilename(path);
		String iniFilesInventoryDir = this.iniFile.getIniFilesDirectory();
		String target = HTMLEdit.append(iniFilesInventoryDir, filename);
		NgFile.copyFile(path, target);
	}
	
	public boolean existsInInventory(String fileInIniFilesDirectory) throws Exception {
		String iniFilesDir = iniFile.getIniFilesDirectory();
		String filename = ServerUtils.getFilename(fileInIniFilesDirectory);
		String test = HTMLEdit.append(iniFilesDir, filename);
		return ServerUtils.exists(test);
	}
	
	public W2AIniFile findInInventory(File webflowArchive) throws Exception {
		String filename = IniFileInventory.unlockKey(webflowArchive.getName());
		String iniFilesInventoryDir = this.iniFile.getIniFilesDirectory();
		String iniFilename = filename.replace(".zip", ".ini");
		String target = HTMLEdit.append(iniFilesInventoryDir, iniFilename);
		W2AIniFile iniFile = new W2AIniFile(target);
		return iniFile;
	}

//  @Deprecated
//	private W2AIniFile findInUploads(File webflowArchive) throws Exception {
//		String filename = webflowArchive.getName();
//		String uploadsDir = this.iniFile.getUploadsDir();
//		String iniFilename = filename.replace(".zip", ".ini");
//		String target = HTMLEdit.append(uploadsDir, iniFilename);
//		W2AIniFile iniFile = new W2AIniFile(target);
//		return iniFile;
//	}
	
	public void copyToResources(File file) throws Exception {
		String path = file.getPath();
		String filename = ServerUtils.getFilename(path);
		String resourceDir = this.iniFile.getResourcesDirectory();
		String target = HTMLEdit.append(resourceDir, filename);
		ServerUtils.mkdirs(resourceDir);
		NgFile.copyFile(path, target);
	}

	/**
	 * Uploads copy & test ... 
	 */
	
	public boolean existsInUploads(String fileInUploadsDirectory) throws Exception {
		String uploadsDir = iniFile.getUploadsDir();
		String filename = ServerUtils.getFilename(fileInUploadsDirectory);
		String test = HTMLEdit.append(uploadsDir, filename);
		return ServerUtils.exists(test);
	}
	
	public void uploadWebflowArchive(W2AIniFile w2aIniFile) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void uploadZipFileToServer(String webflowzipfile) throws Exception {
		String uploadsDir = iniFile.getUploadsDir();
		String filename = ServerUtils.getFilename(webflowzipfile);
		String toPath = HTMLEdit.append(uploadsDir, filename);
		NgFile.copyFile(webflowzipfile, toPath);
	}
	
	public void uploadZipFileToServer(String webflowFile, String newName) throws Exception {
		String uploadsDir = iniFile.getUploadsDir();
		String toPath = HTMLEdit.append(uploadsDir, newName);
		NgFile.copyFile(webflowFile, toPath);
	}
	
	public void deleteFileInUploads(String filename) throws Exception {
		String uploadsDir = iniFile.getUploadsDir();
		String toPath = HTMLEdit.append(uploadsDir, filename);
		NgFile.deleteFile(toPath);
	}
	
	public void copyFileToUploads(String webflowFile, String partialFile) throws Exception {
		String uploadsDir = iniFile.getUploadsDir();
		String filename = ServerUtils.getFilename(partialFile);
		String toPath = HTMLEdit.append(uploadsDir, filename);
		NgFile.deleteFile(toPath);
		NgFile.copyFile(webflowFile, toPath);
	}

	public String uploadAndTagZipFileToServer(String webflowzipfile, String token, UserRequest request) throws Exception {
		String uploadsDir = iniFile.getUploadsDir();
		String filename = InventoryRequestKey.format(request, token, ServerUtils.getFilename(webflowzipfile));
		String toPath = HTMLEdit.append(uploadsDir, filename);
		NgFile.copyFile(webflowzipfile, toPath);
		return toPath;
	}

	public File uploads() throws Exception {
		String uploadsDir = iniFile.getUploadsDir();
		return new File(uploadsDir);
	}

	public boolean uploadsDirExists() throws Exception {
		return uploads().exists();
	}
	
	/**
	 * Uploads copy & test ... 
	 */
	
	public boolean existsInDownloads(String fileInDownloadsDirectory) throws Exception {
		String downloadsDir = iniFile.getDownloadsDir();
		String filename = ServerUtils.getFilename(fileInDownloadsDirectory);
		String test = HTMLEdit.append(downloadsDir, filename);
		return ServerUtils.exists(test);
	}

	public boolean readyForDownloading(String fileInDownloadsDirectory) throws Exception {
		String tokenName = ProcessPrepareUseCase.getTokenizedFilename(fileInDownloadsDirectory);
		return existsInDownloads(tokenName);
	}

	public boolean existsInDiagnostics(String fileInDiagnosticsDir) throws Exception {
		String downloadsDir = iniFile.getDiagnosticsDir();
		String filename = ServerUtils.getFilename(fileInDiagnosticsDir);
		String test = HTMLEdit.append(downloadsDir, filename);
		return ServerUtils.exists(test);
	}
	
	public UploadRequestLogic determineUploadRequest() throws Exception {
		
		UploadRequestLogic logic = new UploadRequestLogic(this);
		logic.input(getXIniFile().getUploadsDir());
		return logic;

	}
	
	/**
	 * Task/Queue management ... 
	 */
	
	public void execute() throws Exception {
		
		if ( !isUploadsDirectoryEmpty() ) {
			UploadRequestLogic queue = determineUploadRequest();
			for ( UploadRequest request : queue.getUploadRequestList().values() ) {
				PrepareIniFileForUse prepare = new PrepareIniFileForUse(this,request);
				request.doTask(this, prepare.getW2AIniFile(), this.getXIniFile());
			}
			
		}
		
	}
	
	public void doTask(TaskInterface task) throws Exception {
		task.doPreConditions();
		if ( task.doUseCaseTrigger() ) {
			task.doPrepareUseCase();
			task.doWishCase();
			task.doCleanupUseCase();
			task.doPostConditions();
		}
	}

	public boolean isUploadsDirectoryEmpty() throws Exception {
		File folder = new File(getXIniFile().getUploadsDir());
		File[] listOfFiles = folder.listFiles();
		return listOfFiles.length == 0;
	}
	
	public boolean isDownloadsDirectoryEmpty() throws Exception {
		File folder = new File(getXIniFile().getDownloadsDir());
		File[] listOfFiles = folder.listFiles();
		return listOfFiles.length == 0;
	}
	
	public boolean isDiagnosticsDirectoryEmpty() throws Exception {
		File folder = new File(getXIniFile().getDiagnosticsDir());
		File[] listOfFiles = folder.listFiles();
		return listOfFiles.length == 0;
	}
	
	public boolean isProcessingDirectoryEmpty() throws Exception {
		File folder = new File(getXIniFile().getProcessingDir());
		File[] listOfFiles = folder.listFiles();
		return listOfFiles.length == 0;
	}
	
	public boolean isProcessingQueue() throws Exception {
		boolean test1 = !isUploadsDirectoryEmpty();
		boolean test2 = !isProcessingDirectoryEmpty();
		return test1 || test2;
	}

	/**
	 * Sub task processing ... 
	 */

	public void injectSkeletonPackage(String workarea) throws Exception {
		this.doTask(new InjectSkeletonPackage(workarea,iniFile));
	}



}

