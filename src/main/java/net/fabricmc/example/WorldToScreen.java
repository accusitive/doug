// package net.fabricmc.example;

// import org.lwjgl.BufferUtils;
// import org.lwjgl.opengl.GL11;

// import net.minecraft.client.util.math.Vector3f;
// import net.minecraft.client.util.math.Vector4f;
// import net.minecraft.util.math.Matrix4f;
// import net.minecraft.util.math.Quaternion;
// import net.minecraft.util.math.Vec2f;

// import java.nio.FloatBuffer;

// public class WorldToScreen {

//     public static Matrix4f getMatrix(int matrix) {
//         FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
//         floatBuffer.get(matrix);
//         // GL11.glGetFloat(matrix, floatBuffer);
//         Matrix4f m = new Matrix4f();
//         m.writeToBuffer(floatBuffer);
//         return m;
//     }

//     public static Vec2f worldToScreen(Vector3f pointInWorld, int screenWidth, int screenHeight) {
//         return worldToScreen(pointInWorld, getMatrix(GL11.GL_MODELVIEW_MATRIX), getMatrix(GL11.GL_PROJECTION_MATRIX), screenWidth, screenHeight);
//     }

//     public static Vec2f worldToScreen(Vector3f pointInWorld, Matrix4f view, Matrix4f projection, int screenWidth,
//             int screenHeight) {
//                 Matrix4f m1 = multiply(new Matrix4f( new Quaternion(pointInWorld.getX(), pointInWorld.getY(), pointInWorld.getZ(), 1.0f)), view);
//                 // Vector4f m2 = new Vector4f(m1., y, z, w)
//                 Matrix4f clipSpacePos = multiply(m1, projection);

//         Vector3f ndcSpacePos = new Vector3f(clipSpacePos.getX() / clipSpacePos.getW(), clipSpacePos.getY() / clipSpacePos.getW(), clipSpacePos.getZ() / clipSpacePos.getW());

// //        System.out.println(pointInNdc);

//         float screenX = ((ndcSpacePos.getX() + 1.0f) / 2.0f) * screenWidth;
//         float screenY = ((1.0f - ndcSpacePos.getY() )/ 2.0f) * screenHeight;

//         // nPlane = -1, fPlane = 1
//         if (ndcSpacePos.getZ() < -1.0 || ndcSpacePos.getZ() > 1.0) {
//             return null;
//         }

//         return new Vec2f(screenX, screenY);
//     }

//     public static Matrix4f multiply(Matrix4f vec, Matrix4f mat) {
//         // IMatrix4f im = ((IMatrix4f) mat);
//         mat.multiply(new Matrix4f(new  Quaternion(vec.getX(), vec.getY(), vec.getZ(), vec.getW())));
//         return mat;
//         // Vector4f v = new Vector4f(mat.)
//         // return new Vector4f(
//         //         vec.x * im.a00() + vec.y * mat.m10 + vec.z * mat.m20 + vec.w * mat.m30,
//         //         vec.x * mat.m01 + vec.y * mat.m11 + vec.z * mat.m21 + vec.w * mat.m31,
//         //         vec.x * mat.m02 + vec.y * mat.m12 + vec.z * mat.m22 + vec.w * mat.m32,
//         //         vec.x * mat.m03 + vec.y * mat.m13 + vec.z * mat.m23 + vec.w * mat.m33
//         // ); 
//         // return new Vector4f();
//     }

// }