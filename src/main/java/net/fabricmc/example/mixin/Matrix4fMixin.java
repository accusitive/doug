package net.fabricmc.example.mixin;

import net.fabricmc.example.IMatrix4f;
import net.minecraft.util.math.Matrix4f;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Matrix4f.class)
public class Matrix4fMixin implements IMatrix4f {
	@Shadow
	public float a00;

	@Shadow
	public float a01;

	@Shadow
	public float a02;

	@Shadow
	public float a03;

	@Shadow
	public float a10;

	@Shadow
	public float a11;

	@Shadow
	public float a12;

	@Shadow
	public float a13;

	@Shadow
	public float a20;

	@Shadow
	public float a21;

	@Shadow
	public float a22;

	@Shadow
	public float a23;

	@Shadow
	public float a30;

	@Shadow
	public float a31;

	@Shadow
	public float a32;

	@Shadow
	public float a33;

	@Override
	public float a00() {
		return this.a00;
	}

	@Override
	public float a01() {
		return this.a01;
	}

	@Override
	public float a02() {
		return this.a02;
	}

	@Override
	public float a03() {
		return this.a03;
	}

	@Override
	public float a10() {
		return this.a10;
	}

	@Override
	public float a11() {
		return this.a11;
	}

	@Override
	public float a12() {
		return this.a12;
	}

	@Override
	public float a13() {
		return this.a13;
	}

	@Override
	public float a20() {
		return this.a20;
	}

	@Override
	public float a21() {
		return this.a21;
	}

	@Override
	public float a22() {
		return this.a22;
	}

	@Override
	public float a23() {
		return this.a23;
	}

	@Override
	public float a30() {
		return this.a30;
	}

	@Override
	public float a31() {
		return this.a31;
	}

	@Override
	public float a32() {
		return this.a32;
	}

	@Override
	public float a33() {
		return this.a33;
	}



	

}
