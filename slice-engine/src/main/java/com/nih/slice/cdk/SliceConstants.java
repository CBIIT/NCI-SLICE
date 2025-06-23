package com.nih.slice.cdk;

public class SliceConstants {

	/** Flag that is set if the chemobject is onpath.
     */
	public final static int ANYWHERE = 0x0000; 
	/** Flag that is set if the chemobject is onpath.
     */
	public final static int ONPATH = 0x0001; 
	/** Flag that is set if the chemobject is offpath.
     */
	public final static int OFFPATH = 0x0002; 
	/** Flag that is set if the chemobject is on ring.
     */
	public final static int ONRING = 0x0004; 
	/** Flag that is set if the chemobject is on ring (on the ring == on the current ring).
     */
	public final static int OFFRING = 0x0008; 
	/** Flag that is set if the chemobject is off the current ring (off the ring == off the current ring)..
     */
	public final static int ONTHEBRIDGE = 0x0010; 
	/** Flag that is set if the chemobject is not on the currentring.
     */
	public final static int OFFTHEBRIDGE = 0x0020; 
	/** Flag that is set if the chemobject is PARTICIPATING.
     */
	public final static int PARTICIPATING = 0x0040; 
	/** Flag that is set if the chemobject PROTECTED.
     */
	public final static int PROTECTED = 0x0080; 
	/** Flag that is set if the chemobject INTERFERING.
     */
	public final static int INTERFERING = 0x0160; 
	/** Flag that is set if the chemobject LEAVING.
     */
	public final static int LEAVING = 0x0320; 
}
