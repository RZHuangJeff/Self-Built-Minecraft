package graphic;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.glDrawElementsInstanced;
import static org.lwjgl.opengl.GL33.glVertexAttribDivisor;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.system.MemoryUtil;

import control.MatrixGenerator;
import display.Cube;

public class InstancedMesh extends Mesh{
    private static final int VECTOR4F_SIZE_FLOAT = 4;
    private static final int VECTOR4F_SIZE_BYTE = InstancedMesh.VECTOR4F_SIZE_FLOAT * 4;
    private static final int INSTANCE_SIZE_FLOAT = 18 + 24;
    private static final int INSTANCE_SIZE_BYTE = INSTANCE_SIZE_FLOAT * 4;

    private int maxSize;
    private int instancedBufferVbo;
    private FloatBuffer instancedBuffer;
    private int textCol, textRow;

    public InstancedMesh(float[] vertices, int[] indices, int maxSize){
        this.maxSize = maxSize;

        setVertices(vertices);
        setIndices(indices);

        glBindVertexArray(vaoId);

        instancedBufferVbo = glGenBuffers();
        vboIds.add(instancedBufferVbo);
        instancedBuffer = MemoryUtil.memAllocFloat(INSTANCE_SIZE_FLOAT * maxSize);
        glBindBuffer(GL_ARRAY_BUFFER, instancedBufferVbo);

        int stride = 0;
        for(int i = 0, loac = 5; i < 4; i++, loac++, stride += VECTOR4F_SIZE_BYTE){
            glVertexAttribPointer(loac, 4, GL_FLOAT, false, INSTANCE_SIZE_BYTE, stride);
            glVertexAttribDivisor(loac, 1);
            glEnableVertexAttribArray(loac);
        }
        glVertexAttribPointer(9, 2, GL_FLOAT, false, INSTANCE_SIZE_BYTE, stride);
        glVertexAttribDivisor(9, 1);
        glEnableVertexAttribArray(9);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void render(ArrayList<Cube> cubes){
        initRender();

        for(int i = 0; i < cubes.size(); i += maxSize){
            int endIndex = Math.min(i + maxSize, cubes.size());
            List<Cube> chunk = cubes.subList(i, endIndex);
            renderChunk(chunk);
        }

        endRender();
    }

    private void renderChunk(List<Cube> cubes){
        int i = 0;
        Vector2f textOffset = new Vector2f();
        for (Cube cube : cubes) {
            Matrix4f worldMatrix = MatrixGenerator.getWorldMatrix(cube.getPosition(), cube.getRotation(), cube.getScale());
            worldMatrix.get(InstancedMesh.INSTANCE_SIZE_FLOAT*i, instancedBuffer);

            textOffset = cube.getTextOffset();
            
            int buffPos = InstancedMesh.INSTANCE_SIZE_FLOAT*i + 16;
            instancedBuffer.put(buffPos, textOffset.x/textCol);
            instancedBuffer.put(buffPos + 1, textOffset.y/textRow);
            i++;
        }

        glBindBuffer(GL_ARRAY_BUFFER, instancedBufferVbo);
        glBufferData(GL_ARRAY_BUFFER, instancedBuffer, GL_DYNAMIC_DRAW);

        glDrawElementsInstanced(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0, cubes.size());
    }

    public void setTextColRow(int col, int row){
        textCol = col;
        textRow = row;
    }

    public int getTextColumn(){
        return textCol;
    }

    public int getTextRow(){
        return textRow;
    }
}