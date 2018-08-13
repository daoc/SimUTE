/*
 *  Simbad - Robot Simulator
 *  Copyright (C) 2004 Louis Hugues
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 -----------------------------------------------------------------------------
 * 07/02/2005 - now use Box Primitive instead of QuadArray
 * $Author: sioulseuguh $ 
 * $Date: 2005/04/25 17:54:58 $
 * $Revision: 1.7 $
 * $Source: /cvsroot/simbad/src/simbad/sim/Box.java,v $
 */
package simbad.sim;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Primitive;
/**
 * A box object which can be put in the environment. 
 * @author Louis Hugues
 */
public class MyPath extends BlockWorldObject {
    
    /** Object dimension.s */
    float sx,sy,sz;
     
    /** Constructs a box object.
     * 
     * @param pos position of the center of the object. 
     * @param extent - extent in the three directions.
     * @param wd	EnvironmentDescription for global parameters.
     */

    
    
    public MyPath(Vector3d pos, EnvironmentDescription wd) {        
        this(pos,new Vector3f(1, 0.02f, 1),wd, wd.green);
    }    
    
    public MyPath(Vector3d pos, Vector3f extent, EnvironmentDescription wd) {        
        this(pos,extent,wd,wd.boxColor);
    }
    
    /** Constructs a box object.
     * 
     * @param pos position of the center of the object. 
     * @param extent - extent in the three directions.
     * @param wd	EnvironmentDescription for global parameters.
     * @param color  color of the object.
     */
    public MyPath(Vector3d pos,  Vector3f extent,EnvironmentDescription wd, Color3f color) {        
        setCanBeTraversed(true);
        this.sx = extent.x;
        this.sy = extent.y;
        this.sz = extent.z;
        // put it on the floor
        pos.y += sy/2;
        create3D(wd,color);
        translateTo(pos);       
    }
    /** Create the 3d object */
    protected void create3D(EnvironmentDescription wd,Color3f color){
        // create the box using a primitive
        super.create3D();
        Material mat = new Material();
        appearance.setMaterial(mat);
        
        int flags = Primitive.GEOMETRY_NOT_SHARED | Primitive.ENABLE_GEOMETRY_PICKING | Primitive.GENERATE_NORMALS;
        flags |= Primitive.ENABLE_APPEARANCE_MODIFY;
        com.sun.j3d.utils.geometry.Box box =new com.sun.j3d.utils.geometry.Box(sx/2,sy/2,sz/2,flags,appearance,0);         
        // disable  sensor  detection 
        box.setPickable(false);
        box.setCollidable(false);
        //  define the boundsfFor collision  detection
//	    BoundingBox bounds = new BoundingBox();
//	    bounds.setUpper(sx/2,sy/2,sz/2);
//	    bounds.setLower(-sx/2,-sy/2,-sz/2);
//	    setBounds(bounds);
	    BoundingBox bounds = new BoundingBox();
	    bounds.setUpper(0,-1,0);
	    bounds.setLower(0,-1,0);
	    setBounds(bounds);
            setColor(color);
            addChild(box);
    }

}

