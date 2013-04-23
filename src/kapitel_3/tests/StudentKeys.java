package kapitel_3.tests;

import kapitel_3.vl.IKey;

public class StudentKeys {
	public static class SurNameKey implements IKey {
		String surName;
		public SurNameKey(String surName) {
			this.surName = surName;
		}
		public boolean equals(Object data) {
			return surName == ((Student) data).surName;
		}
	}
	
	public static class MatrNrKey implements IKey {
		String matrNr;
		public MatrNrKey(String matrNr) {
			this.matrNr = matrNr;
		}
		public boolean equals(Object data) {
			return matrNr == ((Student) data).matrNr;
		}
	}
}
