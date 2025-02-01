package complexfractal.gui;

import complexfractal.FractalParams;
import mathkit.Complex;
import mathkit.RArea;
import mathkit.RField;
import mathkit.ZField;

public final class FieldParams {
	public final ZField size;
	public final RArea area;
	public double step;
	private int zoom;

    // Called in ControlWindow context
	public FieldParams(ZField size) {
		this.size = new ZField();
	    area = new RArea(FractalParams.DEFAULT_FIELD);
	    zoom = FractalParams.get().getZoom();
		setSize(size);
	}
	
    // Called in FullWindow context
	public FieldParams(FieldParams params, ZField size) {
		this.size = new ZField();
		area = new RArea(params.area);
		zoom = params.zoom;
		setSize(size);
	}
	
	private void setSize(ZField size) {
		this.size.set(size);
	    if(size.getRatio() > area.getField().getRatio()) {
	    	step = area.getWidth() / size.getWidth();
	    } else {
	        step = area.getHeight() / size.getHeight();
	    }
	    setField();
	}
	
	public void setZoom(int zoom) {
		step *= this.zoom; 
		step /= zoom;
		this.zoom = zoom;
		setField();
	}
	
	private void setField() {
	    area.setField(RField.factor(size, step));
	}
	
	public void setCenter(Complex center) {
		area.getCenter().set(center);
	}

}
