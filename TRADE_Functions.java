package trade;

//import java.util.logging.Level;
import org.apache.logging.log4j.Level;

import com.hp.lft.sdk.CheckedState;
import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.java.List;
import com.hp.lft.sdk.java.UiObject;
import com.hp.lft.sdk.stdwin.Dialog;
import com.hp.lft.sdk.stdwin.DialogDescription;
import com.hp.lft.sdk.stdwin.EditField;
import com.hp.lft.sdk.stdwin.EditFieldDescription;
//import com.hp.lft.sdk.stdwin.ComboBox;
//import com.hp.lft.sdk.stdwin.ComboBoxDescription;
//import com.hp.lft.sdk.stdwin.ListBox;
//import com.hp.lft.sdk.stdwin.ListBoxDescription;
import com.hp.lft.sdk.stdwin.Window;
import com.hp.lft.sdk.stdwin.WindowDescription;
import com.hp.lft.sdk.web.Button;
import com.hp.lft.sdk.web.ButtonDescription;
//import com.hp.lft.sdk.stdwin.Button;
//import com.hp.lft.sdk.stdwin.ButtonDescription;
import com.hp.lft.sdk.web.Link;
import com.hp.lft.sdk.web.LinkDescription;
import com.hp.lft.sdk.web.ListBox;
import com.hp.lft.sdk.web.ListBoxDescription;
import com.hp.lft.sdk.web.Page;
import com.hp.lft.sdk.web.PageDescription;

import commonoperations.ApplicationFunctions;
import objectrepository.ObjRepo;
import objectrepository.ObjRepo.CitiDirectAppBrw;
import objectrepository.ObjRepo.CitiDirectAppBrw.FormFrame.MainApplet;
import objectrepository.ObjRepo.CitiDirectAppBrw.FormFrame.MainApplet.BenDraweeCountryImpLLB;
import objectrepository.ObjRepo.CitiDirectAppBrw.FormFrame.MainApplet.LibraryLookUpDlg;
import objectrepository.ObjRepo.CitiDirectAppBrw.FormFrame.MainApplet.NewDetailsDlg;

public class TRADE_Functions extends ApplicationFunctions {

	public static void TradeServiceSelect() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);
		NewDetailsDlg newDetailsDlg = objRepo.CitiDirectAppBrw().FormFrame().MainApplet().NewDetailsDlg();
		
		// handle Warning Dialog
		dialogHandle(mainApplet.WarningDlg(), mainApplet.WarningDlg().MessageEdtr(), mainApplet.WarningDlg().OKBtn());

		mainApplet.TabbedPane().select("Input/Modify");

		// if (checkWarningDialog(mainApplet.SummaryTbl(), mainApplet.WarningDlg(), 25))
		// {
		dialogHandle(mainApplet.WarningDlg(), mainApplet.WarningDlg().MessageEdtr(), mainApplet.WarningDlg().OKBtn());
		// }
		clickButton(mainApplet.NewBtn());

		UiObject[] combos = new UiObject[] {

				searchDefinitionDlg.CustomerNameCombo(), searchDefinitionDlg.CustomerIdCombo()

		};
		UiObject[] texts = new UiObject[] { searchDefinitionDlg.CustomerNameTxt(),
				searchDefinitionDlg.SearchCustomerIdTxt()

		};
		String[] fieldNames = new String[] { TransactionInitiator, CustomerID };
		String ColumnName = TradeInitiatorDescription;

		// libraryLookUpNoWarn(newDetailsDlg.TranInitiatorLLB(),combos, texts,
		// fieldNames,ColumnName);//check
		newDetailsDlg.TranInitiatorLLB().exists(1);
		libraryLookUpWarn(newDetailsDlg.TranInitiatorLLB(), combos, texts, fieldNames, ColumnName);

		// selectTableValue(mainApplet.LibraryLookUpDlg().LibraryLookUpTable(),
		// TradeInitiatorDescription);

		// }

		// clickButton(mainApplet.LibraryLookUpDlg().OKBtn());
		clickButton(newDetailsDlg.TranTypeLLB());
		selectTableValue(mainApplet.LibraryLookUpDlg().LibraryLookUpTable(), TransactionType);
		clickButton(newDetailsDlg.CreationMethodLLB());
		selectTableValue(mainApplet.LibraryLookUpDlg().LibraryLookUpTable(), CreationMethod);
		clickButton(newDetailsDlg.OkBtn());

	}

	public static void initImportLCTransaction() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Main");
		enterTextField(mainApplet.ApplLcrefNumTxt(), // OR Name
				ApplicantLCReferenceNumber); // Column Name

		selectValuefromCombo(mainApplet.FormOfDocCreditCombo(), mainApplet.ChoiceList(), FormOfDocumentaryCredit);
		selectValuefromCombo(mainApplet.ApplicableRuleCombo(), mainApplet.ChoiceList(), ApplicableRules);

		/// enterMultilineText(mainApplet.MultilineArwDwn1(),
		/// mainApplet.MultilineEditField() ,s
		enterMultilineText(mainApplet.MultilineArwDwn1(), mainApplet.MultilineEditField(), BeneficiaryNameAddress);

		doLookUp(mainApplet.BenDraweeImpCountryTxt(), BeneficiaryCountry, mainApplet.BenDraweeCountryImpLLB());

		enterTextField(mainApplet.ContactPersonTxt(), ContactPerson);

		enterTextField(mainApplet.ContEmailAddrsTxt(), ContactsEmailAddress);

		enterTextField(mainApplet.TelephoneNumTxt(), TelephoneNumber);

		enterTextField(mainApplet.FaxNumTxt(), FaxNumber);

		enterMultilineText(mainApplet.MultilineArwDwn2(), mainApplet.MultilineEditField(), // OR
				AdvisingBankNameAddress);// Column Name

		doLookUp(mainApplet.AdvBankCountryTxt(), AdvisingBankCountry, mainApplet.AdvBankCountryLLB());

		doLookUp(mainApplet.CcyTxt(), Currency, mainApplet.CcyAmountLLB());

		enterTextField(mainApplet.AmountTxt(), Amount);

		selectValuefromCombo(mainApplet.AvailableByCombo(), mainApplet.ChoiceList(), AvailableBy);
		selectValuefromCombo(mainApplet.AvailableWithCombo(), mainApplet.ChoiceList(), AvailableWith);
		enterMultilineText(mainApplet.MultilineArwDwn3(), mainApplet.MultilineEditField(), BankNameAddress);
		slideMax(mainApplet.MainAppletSlider());
		doLookUp(mainApplet.BankCountryTxt(), BankCountry, mainApplet.BankCountryLLB());

		selectCheckBox(mainApplet.UpToChk(), UpTo);

		enterTextField(mainApplet.ExpiryPlaceTxt(), ExpiryPlace);

		enterDateField(mainApplet.ExpiryDate(), ExpiryDate);

		clickButton(mainApplet.NotifyByLLB());
		selectTableValue(mainApplet.LibraryLookUpDlg().LibraryLookUpTable(), NotifyBy);

		selectCheckBox(mainApplet.DraftReqChk(), DraftsRequired);
		selectValuefromCombo(mainApplet.TenorCombo(), mainApplet.ChoiceList(), Tenor);
		selectValuefromCombo(mainApplet.ConfirmInstrCombo(), mainApplet.ChoiceList(), ConfirmationInstructions);

		if (mainApplet.DaysTxt().isEnabled()) {

			enterTextField(mainApplet.DaysTxt(), Days);
		}

		if (mainApplet.TenorTermsCombo().isEnabled()) {
			selectValuefromCombo(mainApplet.TenorTermsCombo(), mainApplet.ChoiceList(), TenorTerms);
		}

		enterTextField(mainApplet.DrawOnTxt(), DrawnOn);

		enterTextField(mainApplet.ChargesTxt(), Charges);

	}

	// Trade services Conditions//
	public static void TradeImpLcCondition() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Conditions");

		enterTextField(mainApplet.ConditionsTxt(), Condition);

	}

	// Trade services Documents//
	public static void TradeImpLCDocument() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);
		mainApplet.TradeTabPane().select("Documents");

		clickButton(mainApplet.TransportDocumentLLB());
		selectTableValue(mainApplet.LibraryLookUpDlg().LibraryLookUpTable(), TransportationDocument);
		selectValuefromCombo(mainApplet.MarkedFreightCombo(), mainApplet.ChoiceList(), MarkedFreight);
		enterMultilineText(mainApplet.MultilineArwDwn0(), mainApplet.MultilineEditField(), ConsignedTo);
		enterMultilineText(mainApplet.MultilineArwDwn1(), mainApplet.MultilineEditField(), AndNotify);
		enterTextField(mainApplet.AdditionalDocTxt(), AdditionalDocuments);

		// enterTextField(mainApplet.TextTxt(), Text);
		// enterTextField(mainApplet.TextTxt(), Text);ERROR

	}

	public static void Trade() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		String A = getValue(TradeInitiatorDescription);
		System.out.println(A);
	}

	// Trade services Shipment//
	public static void TradeImpLcShipment() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);
		mainApplet.TradeTabPane().select("Shipment");

		selectValuefromCombo(mainApplet.PartialShipmntCombo(), mainApplet.ChoiceList(), PartialShipments);
		selectValuefromCombo(mainApplet.TranShipmntCombo(), mainApplet.ChoiceList(), Transshipments);

		enterTextField(mainApplet.ShipFromTxt(), ShipFrom);

		doLookUp(mainApplet.CountryOfOriginTxt(), CountryOfOrigin, mainApplet.CountryOfOriginLLB());

		enterTextField(mainApplet.PortOfLoadingTxt(), PortofLoading);
		enterTextField(mainApplet.ShipToTxt(), ShipTo);

		enterTextField(mainApplet.PortOfDischargeTxt(), PortofDischarge);
		enterTextField(mainApplet.PortOfDischargeTxt(), PortofDischarge);

		clickButton(mainApplet.ShipmntTermsLLB());
		selectTableValue(mainApplet.LibraryLookUpDlg().LibraryLookUpTable(), ShipmentTerms);

		enterDateField(mainApplet.LatestShipmntDate(), LatestShipmentDate);

	}

	// Trade services Details//
	public static void TradeImpLcDetails() throws Exception

	{
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);
		mainApplet.TradeTabPane().select("Details");

		UiObject[] combos = new UiObject[] {

				searchDefinitionDlg.SubStructIdCombo() };
		UiObject[] texts = new UiObject[] { searchDefinitionDlg.SubStrutIdTxt() };
		String[] fieldNames = new String[] { DetailsLayout };
		if (libraryLookUpNoWarn(mainApplet.DetailsLayoutLLB(), combos, texts, fieldNames)) {

			report(Level.INFO, "Library Look UP value selected", "Library Look Up value set");

		} else {

			report(Level.ERROR, "Library Look Up value not selected", "Library Look Up value not set");

		}
		clickButton(mainApplet.LibraryLookUpDlg().OKBtn());
	}

	// Trade services Goods//
	public static void TradeImpLcGoods() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Goods");

		enterTextField(mainApplet.GoodsTxtBefDetTxt(), GoodsTextBeforeDetails);

		enterTextField(mainApplet.GoodsTxtAftDetTxt(), GoodsTextAfterDetails);

	}

	// Trade services others//
	public static void TradeImpLcOthers() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Others");

		selectValuefromCombo(mainApplet.InsuCoveredByCombo(), mainApplet.ChoiceList(), InsuranceCoveredBy);// check for
																											// if
		// TRADE SUBMIT
		clickButton(mainApplet.SubmitBtn());
		// SUBMIT HANDLE
		clickButton(mainApplet.SubmitDialogDlg().OkBtn());

	}

	public static void TradesubmitTransaction() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		// booleanRtn = true;

		if (errorsDialogHandle()) {
			if (getValue("ErrorValidation").equalsIgnoreCase("YES")) {
				report(Level.INFO, "Passed : Transaction Failed with Error Message", "Error Validation Passed");
			} else {
				report(Level.ERROR, "Failed : Transaction Failed with Error Message", "Transaction Failed with Error");
				// booleanRtn = false;
			}
		} else if (getValue("ErrorValidation").equalsIgnoreCase("YES")) {
			report(Level.ERROR, "Error Validation Failed", "Error Validation Failed");
			// booleanRtn = false;
		}

		if (dialogHandle(navbarApplet.InformationDlg(), navbarApplet.InformationDlg().MessageEdtr(),
				navbarApplet.InformationDlg().OKBtn())) {
			if (getValue("ErrorValidation").equalsIgnoreCase("YES")) {
				report(Level.ERROR, "Failed : Transaction Submitted without Error Message", "Error Validation Failed");
				// booleanRtn = false;
			} else {
				report(Level.INFO, "Transaction Submitted Successfully", "Transaction Submitted Successfully");
			}
		} else if (!(getValue("ErrorValidation").equalsIgnoreCase("YES"))) {
			report(Level.INFO, "Transaction Submission Failed", "Transaction Submission Failed");
			// booleanRtn = false;
		}

		// return true;
	}

	public static void TradeDirectpresentSub() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		dialogHandle(mainApplet.WarningDlg(), mainApplet.WarningDlg().MessageEdtr(), mainApplet.WarningDlg().OKBtn());

		mainApplet.TradeTabPane().select("Main");
		enterTextField(mainApplet.ExpRefNumTxt(), ExporterReferenceNumber);
		enterTextField(mainApplet.AdvBankRefNumTxt(), AdvisingBankReferenceNumber);
		enterTextField(mainApplet.IssBankRefNumTxt(), IssuingBankReferenceNumber);
		enterTextField(mainApplet.ImpRefNumTxt(), ImporterReferenceNumber);
		enterMultilineText(mainApplet.MultilineArwDwn0(), mainApplet.MultilineEditField(), ExporterNameAddress);
		doLookUp(mainApplet.ExporterCounryTxt(), ExporterCountry, mainApplet.ExpCountryLLB());
		enterMultilineText(mainApplet.MultilineArwDwn1(), mainApplet.MultilineEditField(), ImporterNameAddress);
		doLookUp(mainApplet.BenDraweeImpCountryTxt(), ImporterCountry, mainApplet.BenDraweeCountryImpLLB());
		doLookUp(mainApplet.CcyTxt(), Currency, mainApplet.CcyAmountLLB());
		selectValuefromCombo(mainApplet.TenorCombo(), mainApplet.ChoiceList(), Tenor);
		enterTextField(mainApplet.AmountTxt(), Amount);

	}

	public static void TradeDirectpresentSubDetails() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);
		mainApplet.TradeTabPane().select("Details");

		String LOC = getValue(LetterOfCredit);
		System.out.println(LOC);
		String[] LOCvalues = LOC.split("~");
		String LOCcheck = LOCvalues[0];
		String Original = LOCvalues[1];
		String Copies = LOCvalues[2];

		selectCheckBoxValue(mainApplet.DpsLetterOfCreditChk(), LOCcheck);
		enterTextFieldValue(mainApplet.LetterOfCreditOrigTxt(), Original);
		enterTextFieldValue(mainApplet.LetterOfCreditCopyTxt(), Copies);

	}

	public static void TradeGreentradeWindow() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Documents");
		clickButton(mainApplet.UploadDocLink());
		waitTime(5);
		mainApplet.UploadDocLink().highlight();

		Window Tradewindow = Desktop.describe(Window.class, new WindowDescription.Builder()

				.windowClassRegExp("Internet Explorer_TridentDlgFrame")

				.windowTitleRegExp("Global GreenTrade - Document Library Window |Ver : 7.2.02 -- Webpage Dialog")
				.build());

		Tradewindow.activate();
		// Tradewindow.highlight();

		if (Tradewindow.exists()) {
			report(Level.INFO, "TradeWindow", "URL matched");
			logger.log(Level.INFO, "$Window Found : " + Tradewindow);
			// ComboBox DocType = Tradewindow.describe(ComboBox.class, new
			// ComboBoxDescription.Builder();

		}

		Page Tradepage = Tradewindow.describe(Page.class, new PageDescription.Builder().build());

		com.hp.lft.sdk.web.EditField CommentsEdt = Tradepage.describe(com.hp.lft.sdk.web.EditField.class,
				new com.hp.lft.sdk.web.EditFieldDescription.Builder().id("comment").build());
		CommentsEdt.highlight();
		CommentsEdt.setValue(getValue(Comments));

		ListBox DocTypeList = Tradepage.describe(ListBox.class,
				new ListBoxDescription.Builder().name("cdGreenTrade.formName").tagName("SELECT").build());
		DocTypeList.select("AIB:Airway Bill");

		Button AddfilesBtn = Tradepage.describe(Button.class,
				new ButtonDescription.Builder().name("Add Files...").tagName("LABEL").build());
		AddfilesBtn.click();

		// File Upload Dialog
		Dialog FileUploadDlg = Tradewindow.describe(Dialog.class,
				new DialogDescription.Builder().text("Choose File to Upload").build());
		// FileUploadDlg.activate();//
		if (FileUploadDlg.exists()) {
			report(Level.INFO, "FileUploadDlg", "URL matched");
			logger.log(Level.INFO, "$Window Found : " + FileUploadDlg);
		}

		// File Path Set
		EditField FilePath = FileUploadDlg.describe(EditField.class,
				new EditFieldDescription.Builder().windowClassRegExp("Edit").attachedText("File &name:").build());
		FilePath.sendKeys(getValue(TradePath));

		// File Open Button
		com.hp.lft.sdk.stdwin.Button OpenBtn = FileUploadDlg.describe(com.hp.lft.sdk.stdwin.Button.class,
				new com.hp.lft.sdk.stdwin.ButtonDescription.Builder().windowClassRegExp("Button")
						.windowTitleRegExp("&Open").build());

		OpenBtn.click();
		waitTime(3);

		// Upload Button

		Button UploadAllBtn = Tradepage.describe(Button.class,
				new ButtonDescription.Builder().outerText("Upload All").build());
		UploadAllBtn.click();
		
		// Handle Alert
		
		

		// Handle Dialog
		Dialog msgwebpage = Tradewindow.describe(Dialog.class,
				new DialogDescription.Builder().text("Message from webpage").build());

		if (msgwebpage.exists()) {
			com.hp.lft.sdk.stdwin.Button MsgOkBtn = msgwebpage.describe(com.hp.lft.sdk.stdwin.Button.class,
					new com.hp.lft.sdk.stdwin.ButtonDescription.Builder().windowClassRegExp("Button")
							.windowTitleRegExp("OK").build());
			MsgOkBtn.click();
		}
		waitTime(30);
		// Refresh Button
		Button RefreshBtn = Tradepage.describe(Button.class,
				new ButtonDescription.Builder().outerText("Refresh").build());
		RefreshBtn.click();
		waitTime(20);

		// close
		Link CloseLnk = Tradepage.describe(Link.class,
				new LinkDescription.Builder().outerText("Close").tagName("A").build());
		CloseLnk.click();

		// TRADE SUBMIT
		clickButton(mainApplet.SubmitBtn());
		// SUBMIT HANDLE
		clickButton(mainApplet.SubmitDialogDlg().OkBtn());
	}

	public static void DirPresentUploadDocVerify() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		clickButton(mainApplet.UploadDocLink());
		waitTime(5);
		Window Tradewindow = Desktop.describe(Window.class, new WindowDescription.Builder()

				.windowClassRegExp("Internet Explorer_TridentDlgFrame")

				.windowTitleRegExp("Global GreenTrade - Document Library Window |Ver : 7.2.02 -- Webpage Dialog")
				.build());

		Tradewindow.activate();
		// Tradewindow.highlight();

		if (Tradewindow.exists()) {
			report(Level.INFO, "TradeWindow", "URL matched");
			logger.log(Level.INFO, "$Window Found : " + Tradewindow);
			// ComboBox DocType = Tradewindow.describe(ComboBox.class, new
			// ComboBoxDescription.Builder();

		}

		Page Tradepage = Tradewindow.describe(Page.class, new PageDescription.Builder().build());

		// close
		Link CloseLnk = Tradepage.describe(Link.class,
				new LinkDescription.Builder().outerText("Close").tagName("A").build());
		CloseLnk.click();

	}

	public static void DownloadAndSave() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		clickButton(mainApplet.DownloadBtn());

		// File Upload Dialog
		Dialog SaveAsDlg = Desktop.describe(Dialog.class,
				new DialogDescription.Builder().windowTitleRegExp("Save As").build());
		SaveAsDlg.activate();
		if (SaveAsDlg.exists()) {
			report(Level.INFO, "FileUploadDlg", "URL matched");
			logger.log(Level.INFO, "$Window Found : " + SaveAsDlg);
		}

		// File Path Set
		EditField FileName = SaveAsDlg.describe(EditField.class,
				new EditFieldDescription.Builder().windowClassRegExp("Edit").build());
		// System.out.println(getValue(TradePath));
		FileName.sendKeys(getValue(TradePath));

		com.hp.lft.sdk.stdwin.Button SaveBtn = SaveAsDlg.describe(com.hp.lft.sdk.stdwin.Button.class,
				new com.hp.lft.sdk.stdwin.ButtonDescription.Builder().windowTitleRegExp("&Save").build());

		SaveBtn.click();

	}

	public static void TradeDirectCollections() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Main");
		enterTextField(mainApplet.DrawerRefNumTxt(), DrawerReferenceNumber);
		enterMultilineText(mainApplet.MultilineArwDwn1(), mainApplet.MultilineEditField(), DraweeNameAddress);
		doLookUp(mainApplet.BenDraweeImpCountryTxt(), DraweeCountry, mainApplet.BenDraweeCountryImpLLB());
		enterMultilineText(mainApplet.MultilineArwDwn2(), mainApplet.MultilineEditField(), CollectingBankNameAddress);
		doLookUp(mainApplet.CollectBankCountryTxt(), CollectingBankCountry, mainApplet.CollectingBankCountryLLB());
		selectValuefromCombo(mainApplet.TenorCombo(), mainApplet.ChoiceList(), Tenor);
		doLookUp(mainApplet.CcyTxt(), Currency, mainApplet.CcyAmountLLB());

		enterTextField(mainApplet.AmountTxt(), Amount);

		waitTime(2);
		mainApplet.TradeTabPane().select("Conditions");

	}

	public static void TradeDirectCollectionsDocument() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);
		mainApplet.TradeTabPane().select("Documents");

		String BOL = getValue(BillsOfLading);
		System.out.println(BOL);
		String[] BOLvalues = BOL.split("~");
		String BOLcheck = BOLvalues[0];
		String Original = BOLvalues[1];
		String Copies = BOLvalues[2];

		selectCheckBoxValue(mainApplet.BillsOfLadingChk(), BOLcheck);
		enterTextFieldValue(mainApplet.BillsOfLadingOrigTxt(), Original);
		enterTextFieldValue(mainApplet.BillsOfLadingCopyTxt(), Copies);

	}

	public static void TradeCbaDirectCollectionsShipment() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Shipment");
		// selectValuefromCombo(mainApplet.TypeOfExportCombo(), mainApplet.ChoiceList(),
		// TypeOfExport);
		selectValuefromCombo(mainApplet.TypeOfExportCombo(), mainApplet.ChoiceList(), TypeOfExport);
		enterTextField(mainApplet.DescriptionOfGoodsServiceTxt(), DescriptionOfGoodsOrServices);

		doLookUp(mainApplet.CountryOfOriginTxt(), CountryOfOrigin, mainApplet.CountryOfOriginLLB());
		enterTextField(mainApplet.ShipFromTxt(), ShipFrom);
		enterTextField(mainApplet.ShipToTxt(), ShipTo);

		selectValuefromCombo(mainApplet.ModeOfTransportCombo(), mainApplet.ChoiceList(), ModeOfTransport);

		mainApplet.TradeTabPane().select("Details");// details tab

	}

	public static void TradeDirectCollectionsInstructions() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Instructions");
		enterTextField(mainApplet.InstToCollectBankTxt(), InstructionsToCollectingBank);
		enterTextField(mainApplet.InstToServiceBranchTxt(), InstructionsToServicingBank);
		mainApplet.TradeTabPane().select("Tracers");// Tracer Tab
		mainApplet.TradeTabPane().select("Drafts");// Drafts Tab
		mainApplet.TradeTabPane().select("Others");// Others Tab

		// TRADE SUBMIT
		clickButton(mainApplet.SubmitBtn());
		// SUBMIT HANDLE
		clickButton(mainApplet.SubmitDialogDlg().OkBtn());

	}

	// ***************BARCLAYS*****************************//
	public static void BarclaysDirectCollections() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Main");
		enterTextField(mainApplet.DrawerRefNumTxt(), DrawerReferenceNumber);
		enterMultilineText(mainApplet.MultilineArwDwn0(), mainApplet.MultilineEditField(), DrawerNameAddress);
		doLookUp(mainApplet.DrawerCountryTxt(), DrawerCountry, mainApplet.DrawerCountryLLB());
		enterMultilineText(mainApplet.MultilineArwDwn1(), mainApplet.MultilineEditField(), DraweeNameAddress);
		doLookUp(mainApplet.BenDraweeImpCountryTxt(), DraweeCountry, mainApplet.BenDraweeCountryImpLLB());
		enterMultilineText(mainApplet.MultilineArwDwn2(), mainApplet.MultilineEditField(), CollectingBankNameAddress);
		doLookUp(mainApplet.CollectBankCountryTxt(), CollectingBankCountry, mainApplet.CollectingBankCountryLLB());
		selectValuefromCombo(mainApplet.TenorCombo(), mainApplet.ChoiceList(), Tenor);
		doLookUp(mainApplet.CcyTxt(), Currency, mainApplet.CcyAmountLLB());

		enterTextField(mainApplet.AmountTxt(), Amount);

		waitTime(2);
		mainApplet.TradeTabPane().select("Conditions");

	}

	public static void BarclaysDirectCollectionsShipment() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);
		mainApplet.TradeTabPane().select("Shipment");

		enterTextField(mainApplet.GoodsTxt(), Goods);
		enterTextField(mainApplet.TransportationFromTxt(), TransportationFrom);
		enterTextField(mainApplet.TransportationToTxt(), TransportationTo);
		enterDateField(mainApplet.TransportDocDate(), TransportationDocDate);
		enterTextField(mainApplet.VesselNameTxt(), VesselName);

	}

	public static void BarclaysDirectCollectionsInstructions() throws Exception {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.log(Level.INFO, "$CurrentMethod : " + methodName);

		mainApplet.TradeTabPane().select("Instructions");
		enterTextField(mainApplet.InstToCollectBankTxt(), InstructionsToCollectingBank);
		// enterTextField(mainApplet.InstToServiceBranchTxt(),
		// InstructionsToServicingBank);
		// mainApplet.TradeTabPane().select("Tracers");//Tracer Tab
		mainApplet.TradeTabPane().select("Drafts");// Drafts Tab
		mainApplet.TradeTabPane().select("Others");// Others Tab

		// TRADE SUBMIT
		clickButton(mainApplet.SubmitBtn());
		// SUBMIT HANDLE
		clickButton(mainApplet.SubmitDialogDlg().OkBtn());

	}
}
